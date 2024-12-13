package com.springboot.harubi.Domain.Dto.response;

import com.springboot.harubi.Domain.Entity.Plan;

import java.text.SimpleDateFormat;

public class PlanResponseDto { // Plan 엔티티의 필드 이름 그대로 사용
    private String plan_text;
    private String goal_date; // 지정된 날짜 형식으로 반환

    public PlanResponseDto(String plan_text, String goal_date) {
        this.plan_text = plan_text;
        this.goal_date = goal_date;
    }

    public static PlanResponseDto fromEntity(Plan plan) {
        // 날짜 포맷 정의
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");

        // Plan 엔티티의 필드 값 변환 및 반환
        return new PlanResponseDto(
                plan.getPlan_text(),
                formatter.format(plan.getPlan_date()) // 포맷된 goal_date
        );
    }
}