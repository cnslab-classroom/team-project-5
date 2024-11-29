package com.springboot.harubi.Controller;

import com.springboot.harubi.Service.PlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/plan")
@RequiredArgsConstructor
public class PlanController {
    private final PlanService planService;
}