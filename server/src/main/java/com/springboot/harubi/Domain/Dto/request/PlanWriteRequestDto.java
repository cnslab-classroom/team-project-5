package com.springboot.harubi.Domain.Dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlanWriteRequestDto {
    private String goal_text;
    private Date goal_start_date;
    private Date goal_end_date;
}
