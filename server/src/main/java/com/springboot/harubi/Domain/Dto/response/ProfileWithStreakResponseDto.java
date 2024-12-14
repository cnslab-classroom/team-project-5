package com.springboot.harubi.Domain.Dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProfileWithStreakResponseDto {
    private StreakResponseDto streak;       // 스트릭 정보
    private ProfileResponseDto profile;    // 프로필 정보

    public static ProfileWithStreakResponseDto from(StreakResponseDto streak, ProfileResponseDto profile) {
        return new ProfileWithStreakResponseDto(streak, profile);
    }
}
