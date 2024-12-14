package com.springboot.harubi.Domain.Dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ScheduleWriteResponseDto {
    private Long plan_id;
}
