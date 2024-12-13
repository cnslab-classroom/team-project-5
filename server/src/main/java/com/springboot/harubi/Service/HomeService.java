package com.springboot.harubi.Service;

import com.springboot.harubi.Domain.Dto.response.*;
import com.springboot.harubi.Domain.Entity.*;
import com.springboot.harubi.Repository.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Date;
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

    public HomeResponseDto getHomeData(Long memberId, Long studyGroupId) {
        // 현재 날짜 가져오기
        Date today = new Date();

        // Member 조회
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("Member not found"));

        // Goals 조회 및 변환 (현재 날짜 범위 내, 최대 3개)
        // 오늘 날짜에 해당하는 Goals 조회
        List<GoalResponseDto> goalsForToday = goalRepository.findAll().stream()
                .filter(goal -> goal.getGoalDateStatuses().stream()
                        .anyMatch(status -> isSameDay(status.getGoal_date(), today)))
                .map(goal -> {
                    List<GoalDateStatus> filteredStatuses = goal.getGoalDateStatuses().stream()
                            .filter(status -> isSameDay(status.getGoal_date(), today))
                            .collect(Collectors.toList());
                    return new GoalResponseDto(goal, filteredStatuses);
                })
                .collect(Collectors.toList());

        // Plans 조회 및 변환 (현재 날짜에 해당하는 데이터, 시간순 정렬, 3개)
        List<PlanResponseDto> plans = planRepository.findByMemberAndPlanDate(member, today, PageRequest.of(0, 3))
                .stream()
                .sorted(Comparator.comparing(Plan::getPlan_date)) // goal_date 기준 정렬
                .limit(3) // 최대 3개 선택
                .map(PlanResponseDto::fromEntity) // Plan -> PlanResponseDto 변환
                .collect(Collectors.toList());

        // Studies 조회 및 변환 (현재 날짜 범위 내, 종료일 기준 정렬, 최대 3개)
        List<StudyResponseDto> studies = studyRepository.findByStudyGroupAndDateRange(studyGroupId, today, PageRequest.of(0, 3))
                .stream()
                .sorted(Comparator.comparing(Study::getStudy_end_date)) // 종료일 기준 정렬
                .limit(3) // 최대 3개 선택
                .map(StudyResponseDto::fromEntity) // Study -> StudyResponseDto 변환
                .collect(Collectors.toList());

        // 랜덤 Saying 조회 및 변환
        Saying saying = sayingRepository.findRandomSaying()
                .orElseThrow(() -> new IllegalArgumentException("No sayings available"));
        SayingResponseDto sayingDto = SayingResponseDto.fromEntity(saying);

        // HomeResponseDTO 생성 및 반환
        return new HomeResponseDto(
                member.getNickname(),
                goalsForToday,
                plans,
                studies,
                sayingDto
        );
    }
    private boolean isSameDay(Date date1, Date date2) {
        return date1.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate()
                .equals(date2.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate());
    }
}