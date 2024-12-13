package com.springboot.harubi.Domain.Dto.response;

import com.springboot.harubi.Domain.Entity.Plan;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PlanReadResponseDto {
    private String goal_text;
    private boolean goal_status;
}
