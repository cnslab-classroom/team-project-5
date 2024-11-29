package com.springboot.harubi.Service;

import com.springboot.harubi.Domain.Dto.response.*;
import com.springboot.harubi.Domain.Entity.Member;
import com.springboot.harubi.Domain.Entity.Saying;
import com.springboot.harubi.Repository.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HomeService {

    private final MemberRepository memberRepository;
    private final GoalRepository goalRepository;
    private final PlanRepository planRepository;
    private final StudyRepository studyRepository;
    private final SayingRepository sayingRepository;

    public HomeService(MemberRepository memberRepository, GoalRepository goalRepository,
                       PlanRepository planRepository, StudyRepository studyRepository,
                       SayingRepository sayingRepository) {
        this.memberRepository = memberRepository;
        this.goalRepository = goalRepository;
        this.planRepository = planRepository;
        this.studyRepository = studyRepository;
        this.sayingRepository = sayingRepository;
    }

    public HomeResponseDto getHomeData(Long memberId) {
        // Member 조회
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("Member not found"));

        // Goals 조회 및 변환
        List<GoalResponseDto> goals = goalRepository.findTop3ByMember(member)
                .stream()
                .map(GoalResponseDto::fromEntity)
                .collect(Collectors.toList());

        // Plans 조회 및 변환
        List<PlanResponseDto> plans = planRepository.findTop3PlansByMember(member, PageRequest.of(0, 3))
                .stream()
                .map(PlanResponseDto::fromEntity)
                .collect(Collectors.toList());

        // Studies 조회 및 변환
        List<StudyResponseDto> studies = studyRepository.findTop3ByMember(member)
                .stream()
                .map(StudyResponseDto::fromEntity)
                .collect(Collectors.toList());


        // 랜덤 Saying 조회 및 변환
        Saying saying = sayingRepository.findRandomSaying()
                .orElseThrow(() -> new IllegalArgumentException("No sayings available"));
        SayingResponseDto sayingDto = SayingResponseDto.fromEntity(saying);

        // HomeResponseDTO 생성
        return new HomeResponseDto(
                member.getNickname(),
                goals,
                plans,
                studies,
                sayingDto
        );
    }
}
