package com.springboot.harubi.Domain.Dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlanStatisticsListResponseDto {
    private List<PlanStaticsResponseDto> statics;  // 날짜별 통계
    private List<DailyGoalStaticsDto> dailyStatics;  // 목표별 통계
}
