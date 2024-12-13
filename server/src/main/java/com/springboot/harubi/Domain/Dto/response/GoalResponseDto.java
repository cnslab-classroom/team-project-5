package com.springboot.harubi.Domain.Dto.response;

import com.springboot.harubi.Domain.Entity.Goal;
import com.springboot.harubi.Domain.Entity.GoalDateStatus;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class GoalResponseDto {
    private String goal_text;
    private List<Boolean> goalStatuses;

    public GoalResponseDto(Goal goal, List<GoalDateStatus> filteredStatuses) {
        this.goal_text = goal.getGoal_text();
        this.goalStatuses = filteredStatuses.stream()
                .map(GoalDateStatus::isGoal_status)
                .collect(Collectors.toList());
    }
}
