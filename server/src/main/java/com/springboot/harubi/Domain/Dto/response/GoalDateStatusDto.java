package com.springboot.harubi.Domain.Dto.response;

import com.springboot.harubi.Domain.Entity.GoalDateStatus;
import lombok.Data;

@Data
public class GoalDateStatusDto {
    private boolean goal_status;

    public GoalDateStatusDto(GoalDateStatus goalDateStatus) {
        this.goal_status = goalDateStatus.isGoal_status();
    }
}
