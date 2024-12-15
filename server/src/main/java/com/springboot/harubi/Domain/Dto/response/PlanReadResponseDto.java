package com.springboot.harubi.Domain.Dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlanReadResponseDto {
    private String goal_text;
    private boolean goal_status;
    private Long goal_id;
}
