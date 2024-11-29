package com.springboot.harubi.Domain.Dto.response;

import com.springboot.harubi.Domain.Entity.Goal;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GoalResponseDto {
    private Long goal_id; // Goal 엔티티의 필드 이름 그대로 사용
    private String goal_text;
    private String goal_start_date; // Date를 String으로 변환하여 반환
    private String goal_end_date;
    private String goal_date;
    private boolean goal_status;

    public static GoalResponseDto fromEntity(Goal goal) {
        return new GoalResponseDto(
                goal.getGoal_id(),
                goal.getGoal_text(),
                goal.getGoal_start_date().toString(), // Date 타입을 String으로 변환
                goal.getGoal_end_date().toString(),
                goal.getGoal_date().toString(),
                goal.isGoal_status() // boolean 값을 그대로 반환
        );
    }
}
