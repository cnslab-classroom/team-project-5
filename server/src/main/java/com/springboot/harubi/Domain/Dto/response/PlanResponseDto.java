package com.springboot.harubi.Domain.Dto.response;

import com.springboot.harubi.Domain.Entity.Plan;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.format.DateTimeFormatter;

@Getter
@AllArgsConstructor
public class PlanResponseDto {
    private Long plan_id; // Plan 엔티티의 필드 이름 그대로 사용
    private String plan_text;
    private String goal_date; // 지정된 날짜 형식으로 반환

    public static PlanResponseDto fromEntity(Plan plan) {
        // 날짜 포맷 정의
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        // Plan 엔티티의 필드 값 변환 및 반환
        return new PlanResponseDto(
                plan.getPlan_id(),
                plan.getPlan_text(),
                plan.getGoal_date().format(formatter) // 포맷된 goal_date
        );
    }
}
