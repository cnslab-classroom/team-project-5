package com.springboot.harubi.Domain.Dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DailyGoalStaticsDto {
    private String goal_text;
    private int goal_date_achievement;
    private int goal_whole_date;
    private double goal_percent;
}
