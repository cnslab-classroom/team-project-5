package com.springboot.harubi.Controller;

import com.springboot.harubi.Domain.Dto.response.UserProfileResponseDto;
import com.springboot.harubi.Service.ProfileService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/plan")
public class ProfileController {

    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping("{memberId}/profile")
    public UserProfileResponseDto getUserProfile(@PathVariable Long memberId) {
        return profileService.getProfileByMemberId(memberId);
    }
}
