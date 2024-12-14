package com.springboot.harubi.Domain.Dto.request;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ScheduleWriteRequestDto {
    private String plan_text;      // 수정할 텍스트
    private LocalDateTime goal_date; // 수정할 날짜와 시간
}
