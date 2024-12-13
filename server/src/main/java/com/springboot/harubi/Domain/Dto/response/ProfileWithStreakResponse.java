package com.springboot.harubi.Domain.Dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProfileWithStreakResponse {
    private StreakResponseDto streak;
    private ProfileResponseDto profile;
}

