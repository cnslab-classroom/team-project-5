package com.springboot.harubi.Domain.Dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StreakResponseDto {
    private int streakDays;
    private boolean todayGoalCompleted;
}

