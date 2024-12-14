package com.springboot.harubi.Domain.Dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StreakResponseDto {
    private int streakDays;           // 스트릭 연속일
    private boolean todayGoalCompleted; // 오늘 목표 완료 여부
}
