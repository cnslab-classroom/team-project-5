package com.springboot.harubi.Controller;

import com.springboot.harubi.Common.BaseResponse;
import com.springboot.harubi.Domain.Dto.request.PlanWriteRequestDto;
import com.springboot.harubi.Domain.Dto.response.PlanListResponseDto;
import com.springboot.harubi.Domain.Dto.response.PlanWriteResponseDto;
import com.springboot.harubi.Service.PlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/plan")
@RequiredArgsConstructor
public class PlanController {
    private final PlanService planService;

    @PostMapping("{member_id}/daily")
    public BaseResponse writeTodayPlan(@PathVariable("member_id") Long member_id,
                                       @RequestBody PlanWriteRequestDto requestDto) {
        PlanWriteResponseDto response = planService.writePlans(member_id, requestDto);
        return new BaseResponse(HttpStatus.OK.value(), "목표가 추가되었습니다.", response);
    }

//    @GetMapping("{member_id}/daily")
//    public BaseResponse<PlanListResponseDto> readTodayPlan(@PathVariable("member_id") Long member_id) {
//        PlanListResponseDto response = planService.getTodayPlans(member_id);
//        return new BaseResponse<>(HttpStatus.OK.value(), null, response);
//    }
}