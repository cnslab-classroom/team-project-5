package com.springboot.harubi.Domain.Dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class HomeResponseDto {
    private String nickname; // 사용자 닉네임
    private List<GoalResponseDto> goals; // 목표 리스트
    private List<PlanResponseDto> plans; // 일정 리스트
    private List<StudyResponseDto> studies; // 학습 리스트
    private SayingResponseDto saying; // 랜덤 격언
}
