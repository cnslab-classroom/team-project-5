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

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/plan")
@RequiredArgsConstructor
public class ProfileController {
    private final ProfileService profileService;
    private final StreakService streakService;

    // 프로필 조회
    @GetMapping("/{memberId}/profile")
    public ResponseEntity<ProfileResponseDto> getProfile(@PathVariable Long memberId) {
        ProfileResponseDto profile = profileService.getProfile(memberId);
        return ResponseEntity.ok(profile);
    }

    //프로필 수정
    @PatchMapping("/{memberId}/edit_profile")
    public ResponseEntity<Map<String, String>> updateProfile(
            @PathVariable Long memberId,
            @RequestBody ProfileUpdateRequestDto requestDto) {

        // 서비스 호출
        profileService.updateProfile(memberId, requestDto);

        // 응답 메시지 생성
        Map<String, String> response = new HashMap<>();
        response.put("status", "200");

        if (requestDto.getName() != null) {
            response.put("name", "이름이 성공적으로 수정되었습니다.");
        }
        if (requestDto.getIcon() != null) {
            response.put("icon", "아이콘이 성공적으로 수정되었습니다.");
        }
        if (requestDto.getStatus() != null) {
            response.put("statusMessage", "상태가 성공적으로 수정되었습니다."); // "status" 충돌 방지
        }
        if (requestDto.getBio() != null) {
            response.put("bio", "소개가 성공적으로 수정되었습니다.");
        }
        if (requestDto.getAffiliation() != null) {
            response.put("affiliation", "소속이 성공적으로 수정되었습니다.");
        }

        return ResponseEntity.ok(response);
    }

}

