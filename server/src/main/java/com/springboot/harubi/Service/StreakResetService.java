package com.springboot.harubi.Service;

import com.springboot.harubi.Domain.Entity.Streak;
import com.springboot.harubi.Repository.StreakRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StreakResetService {
    private final StreakRepository streakRepository;

    // 특정 사용자의 스트릭 초기화
    @Transactional
    public void resetStreak(Long memberId) {
        Streak streak = streakRepository.findByMemberId(memberId)
                .orElse(null);

        if (streak != null) {
            streak.resetDays(); // 스트릭 초기화
            streakRepository.save(streak);
        }
    }
}

