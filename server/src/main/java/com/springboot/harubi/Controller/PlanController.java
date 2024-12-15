package com.springboot.harubi.Controller;

import com.springboot.harubi.Common.BaseResponse;
import com.springboot.harubi.Domain.Dto.request.PlanCheckRequestDto;
import com.springboot.harubi.Domain.Dto.request.PlanWriteRequestDto;
import com.springboot.harubi.Domain.Dto.request.ProfileUpdateRequestDto;
import com.springboot.harubi.Domain.Dto.request.ScheduleWriteRequestDto;
import com.springboot.harubi.Domain.Dto.response.*;
import com.springboot.harubi.Service.PlanService;
import com.springboot.harubi.Service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/plan")
@RequiredArgsConstructor
public class PlanController {
    private final PlanService planService;

    @PostMapping("/{member_id}/daily")
    public BaseResponse writeTodayPlan(@PathVariable("member_id") Long member_id,
                                       @RequestBody PlanWriteRequestDto requestDto) {
        PlanWriteResponseDto response = planService.writePlans(member_id, requestDto);
        return new BaseResponse(HttpStatus.OK.value(), "목표가 추가되었습니다.", response);
    }

    @GetMapping("/{member_id}/daily")
    public BaseResponse<PlanListResponseDto> readTodayPlan(@PathVariable("member_id") Long member_id) {
        PlanListResponseDto response = planService.getTodayPlans(member_id);
        return new BaseResponse<>(HttpStatus.OK.value(), null, response);
    }

    @PutMapping("/{member_id}/daily")
    public BaseResponse<PlanCheckResponseDto> checkTodayPlan(
            @PathVariable("member_id") Long member_id,
            @RequestBody PlanCheckRequestDto requestDto) {

        // 서비스 호출
        PlanCheckResponseDto response = planService.checkPlans(member_id, requestDto);

        return new BaseResponse<>(HttpStatus.OK.value(), "목표 상태가 업데이트 되었습니다.", response);
    }

    @GetMapping("/{member_id}/statics")
    public BaseResponse<PlanStatisticsListResponseDto> staticsPlan(@PathVariable("member_id") Long member_id) {
        PlanStatisticsListResponseDto response = planService.getStatics(member_id);
        return new BaseResponse<>(HttpStatus.OK.value(), null, response);
    }

    @PostMapping("/{member_id}/schedule")
    public BaseResponse<ScheduleWriteResponseDto> updatePlan(@PathVariable("member_id") Long member_id,
                                                         @RequestBody ScheduleWriteRequestDto requestDto) {
        ScheduleWriteResponseDto response = planService.updatePlan(member_id, requestDto);
        return new BaseResponse<>(HttpStatus.OK.value(), "일정이 추가되었습니다.", response);
    }

}