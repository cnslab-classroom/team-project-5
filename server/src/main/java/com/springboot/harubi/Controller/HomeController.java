package com.springboot.harubi.Controller;

import com.springboot.harubi.Domain.Dto.response.HomeResponseDto;
import com.springboot.harubi.Service.HomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/plan")
@RequiredArgsConstructor
public class HomeController {

    private final HomeService homeService;

    @GetMapping("/{member_id}")
    public ResponseEntity<HomeResponseDto> getHomeData(@PathVariable("member_id") Long member_id) {
        HomeResponseDto homeData = homeService.getHomeData(member_id);
        return ResponseEntity.ok(homeData);
    }
}
