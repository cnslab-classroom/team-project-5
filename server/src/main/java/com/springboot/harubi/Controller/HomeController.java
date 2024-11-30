package com.springboot.harubi.Controller;

import com.springboot.harubi.Domain.Dto.response.HomeResponseDto;
import com.springboot.harubi.Service.HomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/plan")
@RequiredArgsConstructor
public class HomeController {

    private final HomeService homeService;

    /**
     * Home 데이터를 반환하는 API
     * @param memberId 회원 ID
     * @return HomeResponseDto
     */
    @GetMapping
    public ResponseEntity<HomeResponseDto> getHomeData(@RequestParam Long memberId, @RequestParam Long studyGroupId) {
        HomeResponseDto homeData = homeService.getHomeData(memberId, studyGroupId);
        return ResponseEntity.ok(homeData);
    }
}
