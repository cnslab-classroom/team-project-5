package com.springboot.harubi.Controller;

import com.springboot.harubi.Domain.Dto.request.ProfileUpdateRequestDto;
import com.springboot.harubi.Domain.Dto.response.ProfileResponseDto;
import com.springboot.harubi.Domain.Dto.response.ProfileWithStreakResponse;
import com.springboot.harubi.Domain.Dto.response.StreakResponseDto;
import com.springboot.harubi.Domain.Dto.response.UserProfileResponseDto;
import com.springboot.harubi.Service.ProfileService;
import com.springboot.harubi.Service.StreakService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/profile")
@RequiredArgsConstructor
public class ProfileController {
    private final ProfileService profileService;
    private final StreakService streakService;

    // 프로필 및 스트릭 조회
    @GetMapping("/{memberId}")
    public ResponseEntity<ProfileResponseDto> getProfile(@PathVariable Long memberId) {
        ProfileResponseDto profile = profileService.getProfile(memberId);
        return ResponseEntity.ok(profile);
    }

    // 프로필 수정
    @PutMapping("/{memberId}")
    public ResponseEntity<String> updateProfile(@PathVariable Long memberId, @RequestBody ProfileUpdateRequestDto request) {
        profileService.updateProfile(memberId, request);
        return ResponseEntity.ok("프로필이 성공적으로 수정되었습니다.");
    }
}

