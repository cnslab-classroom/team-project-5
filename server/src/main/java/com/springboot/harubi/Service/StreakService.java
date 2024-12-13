package com.springboot.harubi.Service;

import com.springboot.harubi.Domain.Dto.response.StreakResponseDto;
import com.springboot.harubi.Domain.Entity.GoalDateStatus;
import com.springboot.harubi.Domain.Entity.Streak;
import com.springboot.harubi.Repository.GoalDateStatusRepository;
import com.springboot.harubi.Repository.StreakRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StreakService {
    private final GoalDateStatusRepository goalDateStatusRepository;
    private final StreakRepository streakRepository;

    // 스트릭 조회
    public StreakResponseDto getStreak(Long memberId) {
        Streak streak = streakRepository.findByMemberId(memberId)
                .orElseThrow(() -> new IllegalArgumentException("스트릭 정보를 찾을 수 없습니다."));

        // 오늘 목표 상태 확인
        List<GoalDateStatus> todayStatuses = goalDateStatusRepository.findStatusesByMemberAndDate(memberId, LocalDate.now());
        boolean allCompleted = todayStatuses.stream().allMatch(GoalDateStatus::isGoal_status);

        return new StreakResponseDto(streak.getDays(), allCompleted);
    }

    // 스트릭 업데이트
    @Transactional
    public int updateStreak(Long memberId) {
        LocalDate today = LocalDate.now();

        // 오늘 날짜의 GoalDateStatus 확인
        List<GoalDateStatus> todayStatuses = goalDateStatusRepository.findStatusesByMemberAndDate(memberId, today);

        // 오늘 목표가 없는 경우 스트릭 초기화
        if (todayStatuses.isEmpty() || todayStatuses.stream().noneMatch(GoalDateStatus::isGoal_status)) {
            resetStreak(memberId);
            return 0; // 스트릭 초기화 후 0 반환
        }

        // 오늘 목표를 모두 완료한 경우 스트릭 증가
        boolean allCompleted = todayStatuses.stream().allMatch(GoalDateStatus::isGoal_status);

        Streak streak = streakRepository.findByMemberId(memberId)
                .orElse(new Streak(memberId, 0)); // 스트릭이 없으면 생성

        if (allCompleted) {
            streak.incrementDays();
        } else {
            streak.resetDays();
        }

        streakRepository.save(streak);
        return streak.getDays();
    }

    // 스트릭 초기화 메서드
    @Transactional
    public void resetStreak(Long memberId) {
        Streak streak = streakRepository.findByMemberId(memberId)
                .orElse(new Streak(memberId, 0));

        streak.resetDays();
        streakRepository.save(streak);
    }
}
