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
@RequestMapping("/plan")
@RequiredArgsConstructor
public class ProfileController {
    private final ProfileService profileService;
    private final StreakService streakService;

    // 프로필 조회
    @GetMapping("/{memberId}/profile")
    public ResponseEntity<ProfileWithStreakResponse> getProfileWithStreak(@PathVariable Long memberId) {
        var profile = profileService.getProfile(memberId);
        var streak = streakService.getStreak(memberId);
        return ResponseEntity.ok(new ProfileWithStreakResponse(streak, profile));
    }

    // 프로필 수정
    @PutMapping("/{memberId}/profile_edit")
    public ResponseEntity<String> updateProfile(@PathVariable Long memberId, @RequestBody ProfileUpdateRequestDto request) {
        profileService.updateProfile(memberId, request);
        return ResponseEntity.ok("프로필이 성공적으로 수정되었습니다.");
    }
}

