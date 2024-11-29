package com.springboot.harubi.Controller;


import com.springboot.harubi.Domain.Dto.response.HomeResponseDto;
import com.springboot.harubi.Service.HomeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home")
public class HomeController {

    private final HomeService homeService;

    public HomeController(HomeService homeService) {
        this.homeService = homeService;
    }

    @GetMapping
    public ResponseEntity<HomeResponseDto> getHomeData(@RequestParam Long memberId) {
        HomeResponseDto homeData = homeService.getHomeData(memberId);
        return ResponseEntity.ok(homeData);
    }
}
