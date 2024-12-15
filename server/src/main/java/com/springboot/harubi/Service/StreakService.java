package com.springboot.harubi.Service;

import com.springboot.harubi.Domain.Dto.response.GoalDateStatusDto;
import com.springboot.harubi.Domain.Dto.response.StreakResponseDto;
import com.springboot.harubi.Domain.Entity.GoalDateStatus;
import com.springboot.harubi.Domain.Entity.Member;
import com.springboot.harubi.Domain.Entity.Streak;
import com.springboot.harubi.Repository.GoalDateStatusRepository;
import com.springboot.harubi.Repository.MemberRepository;
import com.springboot.harubi.Repository.StreakRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StreakService {
    private final GoalDateStatusRepository goalDateStatusRepository;
    private final StreakRepository streakRepository;
    private final MemberRepository memberRepository;
    private final StreakResetService streakResetService; // StreakResetService 주입

    @Transactional
    public int updateStreak(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("Member not found" + memberId));

        Streak streak = streakRepository.findByMemberId(memberId)
                .orElse(new Streak(member, 0));

        // 완료된 목표 가져오기 (날짜 기준 내림차순으로 정렬)
        List<GoalDateStatus> completedGoals = goalDateStatusRepository.findCompletedGoalsByMember(memberId);

        AtomicBoolean todayGoalCompleted = new AtomicBoolean(false);

        // 연속적으로 완료된 목표 개수 계산
        int consecutiveDays = calculateConsecutiveStreak(memberId, todayGoalCompleted);

        System.out.println("연속 완료된 목표 개수 계산 완료: " + consecutiveDays);
        System.out.println("오늘 목표 완료 여부: " + todayGoalCompleted.get());

        // 스트릭 일수 업데이트
        streak.setDays(consecutiveDays);
        streak.setMember(member);
        member.setStreak(streak);
        streakRepository.save(streak);

        return streak.getDays();
    }

    private int calculateConsecutiveStreak(Long memberId, AtomicBoolean todayGoalCompleted) {
        int streak = 0;
        LocalDate currentDate = LocalDate.now();

        while (true) {
            // 특정 날짜에 해당하는 goal_date_status를 가져옴
            List<GoalDateStatus> dailyGoals = goalDateStatusRepository
                    .findGoalsByDateAndMember(currentDate.minusDays(streak), memberId);

            // 전체 목표 개수와 완료된 목표 개수 계산
            long totalGoals = dailyGoals.size();
            long completedGoals = dailyGoals.stream().filter(GoalDateStatus::isGoal_status).count();

            System.out.println("goalDate: " + currentDate.minusDays(streak)
                    + ", totalGoals: " + totalGoals
                    + ", completedGoals: " + completedGoals);

            // 오늘 날짜의 목표 상태 설정
            if (streak == 0) {
                todayGoalCompleted.set(totalGoals > 0 && totalGoals == completedGoals);
            }

            // 목표가 하나라도 완료되지 않았으면 streak 종료
            if (totalGoals == 0 || totalGoals != completedGoals) {
                break;
            }

            streak++;
        }

        System.out.println("Final streak: " + streak);
        return streak;
    }

    private boolean isWithinGoalPeriod(GoalDateStatus goal) {
        Date startDate = goal.getGoal().getGoal_start_date();
        Date endDate = goal.getGoal().getGoal_end_date();
        Date goalDate = goal.getGoal_date();

        return !goalDate.before(startDate)&& !goalDate.after(endDate);
    }

    private LocalDate convertToLocalDate(Date date) {
        if (date instanceof java.sql.Date) {
            return ((java.sql.Date) date).toLocalDate();
        } else {
            return date.toInstant()
                    .atZone(java.time.ZoneId.systemDefault())
                    .toLocalDate();
        }
    }

    // 자정 기준으로 목표가 없는 사용자의 스트릭 초기화
    @Scheduled(cron = "0 0 0 * * ?") // 매일 자정 실행
    @Transactional
    public void resetStreaksForMissingGoals() {
        // 모든 사용자 ID 가져오기
        List<Long> memberIds = memberRepository.findAllMemberIds();

        for (Long memberId : memberIds) {
            Date today = new Date();
            // 자정 기준으로 오늘의 목표 데이터가 있는지 확인
            boolean hasGoals = goalDateStatusRepository
                    .findStatusesByMemberAndDate(memberId, today)
                    .stream()
                    .anyMatch(goalDateStatusDto -> goalDateStatusDto.isGoal_status());

            // 자정까지 목표 데이터가 없었던 경우에만 스트릭 초기화
            if (!hasGoals) {
                streakResetService.resetStreak(memberId); // StreakResetService를 통해 초기화
            }
        }
    }

    // 스트릭 조회
    public StreakResponseDto getStreak(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("Member not found: " + memberId));

        Streak streak = streakRepository.findByMemberId(memberId)
                .orElseGet(() -> {
                    Streak newStreak = new Streak(member, 0); // 새 스트릭 생성
                    streakRepository.save(newStreak);          // 새 스트릭을 DB에 저장
                    return newStreak;                          // 저장된 스트릭 반환
                });

        // 오늘의 GoalDateStatus를 조회하고 DTO로 변환
        Date today = new Date();
        List<GoalDateStatusDto> todayStatuses = goalDateStatusRepository
                .findStatusesByMemberAndDate(memberId,today)
                .stream()
                .map(GoalDateStatusDto::new) // GoalDateStatus -> GoalDateStatusDto 변환
                .collect(Collectors.toList());

        // 오늘 목표 완료 여부 확인
        boolean todayGoalCompleted = todayStatuses.stream()
                .allMatch(GoalDateStatusDto::isGoal_status);

        // StreakResponseDto 반환
        return new StreakResponseDto(streak.getDays(), todayGoalCompleted);
    }
}
