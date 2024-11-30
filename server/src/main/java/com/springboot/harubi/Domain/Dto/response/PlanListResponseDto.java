package com.springboot.harubi.Domain.Dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlanListResponseDto {
    private List<PlanReadResponseDto> goal;
}
