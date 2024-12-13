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
        return new StreakResponseDto(streak.getDays(), "오늘 목표를 완료했어요!");
    }

    // 스트릭 업데이트
    @Transactional
    public int updateStreak(Long memberId) {
        // 오늘 날짜의 GoalDateStatus를 확인
        List<GoalDateStatus> todayStatuses = goalDateStatusRepository.findByMemberIdAndGoalDate(memberId, LocalDate.now());

        // 오늘 목표가 없는 경우 스트릭 초기화
        if (todayStatuses.isEmpty()) {
            resetStreakIfNecessary(memberId);
            return 0; // 스트릭 초기화
        }

        // 오늘 목표 상태 확인
        boolean allCompleted = todayStatuses.stream()
                .allMatch(GoalDateStatus::isGoal_status);

        // 스트릭 가져오기 (없으면 새로 생성)
        Streak streak = streakRepository.findByMemberId(memberId)
                .orElse(new Streak(memberId, 0));

        if (allCompleted) {
            streak.incrementDays();
        } else {
            streak.resetDays();
        }

        streakRepository.save(streak);
        return streak.getDays();
    }

    // 자정을 기준으로 목표가 없는 경우 스트릭 초기화
    @Transactional
    public void resetStreakIfNecessary(Long memberId) {
        Streak streak = streakRepository.findByMemberId(memberId)
                .orElse(null);

        if (streak != null && streak.getDays() > 0) {
            streak.resetDays();
            streakRepository.save(streak);
        }
    }
}
