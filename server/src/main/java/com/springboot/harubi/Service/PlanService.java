package com.springboot.harubi.Service;

import com.springboot.harubi.Domain.Dto.request.PlanWriteRequestDto;
import com.springboot.harubi.Domain.Dto.response.AuthLoginResponseDto;
import com.springboot.harubi.Domain.Dto.response.PlanListResponseDto;
import com.springboot.harubi.Domain.Dto.response.PlanResponseDto;
import com.springboot.harubi.Domain.Dto.response.PlanWriteResponseDto;
import com.springboot.harubi.Domain.Entity.Goal;
import com.springboot.harubi.Domain.Entity.Member;
import com.springboot.harubi.Exception.BaseException;
import com.springboot.harubi.Repository.GoalRepository;
import com.springboot.harubi.Repository.MemberRepository;
import com.springboot.harubi.Repository.PlanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional
public class PlanService {
    private final PlanRepository planRepository;
    private final GoalRepository goalRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public PlanWriteResponseDto writePlans(Long member_id, PlanWriteRequestDto planWriteRequestDto) {
        validatePlanWriteRequest(planWriteRequestDto);

        Member member = memberRepository.findById(member_id)
                .orElseThrow(()->new BaseException(404, "존재하지 않는 회원입니다."));

        Goal savedGoal = goalRepository.save(converToGoal(planWriteRequestDto, member));

        return new PlanWriteResponseDto(savedGoal.getGoal_id());
    }

    private Goal converToGoal(PlanWriteRequestDto planWriteRequestDto, Member member) {
        return new Goal(planWriteRequestDto, member);
    }

    private void validatePlanWriteRequest(PlanWriteRequestDto planWriteRequestDto) {
        if (planWriteRequestDto.getGoal_text() == null) {
            throw new BaseException(400, "목표 내용을 입력해 주세요.");
        }
        if (planWriteRequestDto.getGoal_start_date() == null) {
            throw new BaseException(400, "목표 시작 날짜를 입력해 주세요.");
        }
        if (planWriteRequestDto.getGoal_end_date() == null) {
            throw new BaseException(400, "목표 종료 날짜를 입력해 주세요.");
        }
    }

    public PlanListResponseDto getTodayPlans(Long member_id) {
        Member member = memberRepository.findById(member_id)
                .orElseThrow(() -> new BaseException(404, "존재하지 않는 회원입니다."));

        List<Goal> todayGoals = goalRepository.findTodayGoalsByMember(member);

        List<PlanResponseDto> goalDtos = todayGoals.stream()
                .map(goal -> new PlanResponseDto(goal.getGoal_text(), goal.isGoal_status()))
                .collect(Collectors.toList());

        return new PlanListResponseDto(goalDtos);
    }
}
