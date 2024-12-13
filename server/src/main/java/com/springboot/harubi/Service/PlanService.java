package com.springboot.harubi.Service;

import com.springboot.harubi.Domain.Dto.request.PlanWriteRequestDto;
import com.springboot.harubi.Domain.Dto.response.*;
import com.springboot.harubi.Domain.Entity.Goal;
import com.springboot.harubi.Domain.Entity.Member;
import com.springboot.harubi.Exception.BaseException;
import com.springboot.harubi.Repository.GoalRepository;
import com.springboot.harubi.Repository.MemberRepository;
import com.springboot.harubi.Repository.PlanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional
public class PlanService {
    private final PlanRepository planRepository;
    private final GoalRepository goalRepository;
    private final MemberRepository memberRepository;
    private final GoalDateStatusRepository goalDateStatusRepository;

    @Transactional
    public PlanWriteResponseDto writePlans(Long member_id, PlanWriteRequestDto planWriteRequestDto) {
        validatePlanWriteRequest(planWriteRequestDto);

        Member member = memberRepository.findById(member_id)
                .orElseThrow(()->new BaseException(404, "존재하지 않는 회원입니다."));

        Goal savedGoal = saveGoal(planWriteRequestDto, member);

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

        Date today = new Date();
        List<Goal> goals = goalRepository.findByMember(member);

        List<PlanReadResponseDto> goalDtos = goals.stream()
                .map(goal -> {
                    GoalDateStatus todayGoalStatus = goal.getGoalDateStatuses().stream()
                            .filter(status -> isSameDay(status.getGoal_date(), today))
                            .findFirst()
                            .orElseThrow(() -> new BaseException(404, "오늘의 목표 상태가 없습니다."));
                    // 오늘 날짜의 상태 바탕으로 반환할 Dto
                    return new PlanReadResponseDto(goal.getGoal_text(), todayGoalStatus.isGoal_status());
                })
                .collect(Collectors.toList());

        return new PlanListResponseDto(goalDtos);
    }

    private boolean isSameDay(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(date1);
        cal2.setTime(date2);
        return cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
                cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR);
    }

    public PlanCheckResponseDto checkPlans(Long member_id, PlanCheckRequestDto planCheckRequestDto) {
        Date today = getTodayDate();

        Member member = memberRepository.findById(member_id)
                .orElseThrow(()->new BaseException(404, "존재하지 않는 회원입니다."));

        Goal goal = member.getGoals().stream()
                .filter(g -> g.getGoal_id().equals(planCheckRequestDto.getGoal_id()))
                .findFirst()
                .orElseThrow(() -> new BaseException(404, "존재하지 않는 목표입니다."));

        GoalDateStatus dateStatus = goal.getGoalDateStatuses().stream()
                .filter(s -> isSameDay(s.getGoal_date(), today))
                .findFirst()
                .orElseThrow(() -> new BaseException(404, "오늘 날짜의 상태를 찾을 수 없습니다."));

        // 상태 업데이트
        dateStatus.setGoal_status(planCheckRequestDto.isPlan_status());
        goalDateStatusRepository.save(dateStatus);

        return new PlanCheckResponseDto(dateStatus.getStatus_id());
    }

    private Date getTodayDate() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    public PlanStatisticsListResponseDto getStatics(Long member_id) {
        Member member = memberRepository.findById(member_id)
                .orElseThrow(() -> new BaseException(404, "존재하지 않는 회원입니다."));

        // 날짜별 achievement 데이터 가져오기
        List<PlanStaticsResponseDto> statics = getGoalStatics(member);

        // 목표별 achievement 데이터 가져오기
        List<DailyGoalStaticsDto> dailyStatics = getDailyStatics(member);

        return new PlanStatisticsListResponseDto(statics, dailyStatics);
    }

    private List<PlanStaticsResponseDto> getGoalStatics(Member member) {
        // 월요일부터 일요일까지 쭉 가져오는데, 7일×54주를 가져와야함
        // 하루하루의 달성률(그날 goal들의 달성률 계산해서)과 날짜를 반환해줘야함

        // 오늘 날짜 기준 53주 범위 계산
        Date[] dateRange = calculateDateRange(new Date());
        Date startDate = dateRange[0];
        Date endDate = dateRange[1];

        // 53위 범위의 GoalDateStatus 가져오기
        List<GoalDateStatus> statuses = goalDateStatusRepository.findByMemberAndDateRange(member, startDate, endDate);

        // 날짜별로 데이터 그룹화
        Map<Date, List<GoalDateStatus>> groupedByDate = statuses.stream()
                .collect(Collectors.groupingBy(GoalDateStatus::getGoal_date));

        // 날짜별 데이터 처리
        List<PlanStaticsResponseDto> result = new ArrayList<>();
        groupedByDate.entrySet().stream()
                .sorted(Map.Entry.comparingByKey()) // 날짜 순 정렬
                .forEach(entry -> {
                    Date date = entry.getKey();
                    List<GoalDateStatus> dailyStatuses = entry.getValue();

                    int totalGoals = dailyStatuses.size();
                    int achievedGoals = (int) dailyStatuses.stream().filter(GoalDateStatus::isGoal_status).count();
                    double achievementRate = totalGoals == 0 ? 0 : (double) achievedGoals / totalGoals * 100;

                    // 날짜를 하루 더해서 추가
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(date);
                    cal.add(Calendar.DATE, 1); // 하루 추가
                    Date adjustedDate = cal.getTime();

                    result.add(new PlanStaticsResponseDto(adjustedDate, achievementRate));
                });
        return result;
    }

    private Date[] calculateDateRange(Date today) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);

        // 주의 마지막 날(일요일) 설정
        if (calendar.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
            calendar.add(Calendar.DATE, Calendar.SUNDAY - calendar.get(Calendar.DAY_OF_WEEK));
        }
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        Date endDate = calendar.getTime();

        // 주의 시작일(월요일) 설정
        calendar.add(Calendar.WEEK_OF_YEAR, -52); // -52로 바꿔야 53주로 설정
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Date startDate = calendar.getTime();

        return new Date[]{startDate, endDate};
    }

    private List<DailyGoalStaticsDto> getDailyStatics(Member member) {
        // 오늘 날짜에 해당하는 goal들을 가지고 오는데, goal_text, 달성여부, (end_date)-(start_date)+1 계산해서 계획을 달성해야하는 전체 일 수,
        // 달성여부가 true인 날을 count한것, 그리고 달성률을 계산해서 보여줘야함
        List<DailyGoalStaticsDto> result = new ArrayList<>();

        // 오늘 날짜 기준 목표들 가져오기
        Date today = new Date();
        List<Goal> todayGoals = goalRepository.findTodayGoalsByMember(member);

        for (Goal goal : todayGoals) {
            List<GoalDateStatus> statuses = goal.getGoalDateStatuses();

            long achievedDays = statuses.stream()
                    .filter(GoalDateStatus::isGoal_status)
                    .count();

            // 목표의 전체 기간 일수 계산
            long totalDays = (goal.getGoal_end_date().getTime() - goal.getGoal_start_date().getTime())
                    / (1000 * 60 * 60 * 24) + 1;

            // 달성률 계산
            double achievementRate = (totalDays == 0) ? 0.0 : ((double) achievedDays / totalDays) * 100;

            result.add(new DailyGoalStaticsDto(
                    goal.getGoal_text(),
                    (int) achievedDays,
                    (int) totalDays,
                    achievementRate
            ));
        }

        return result;
    }

    @Transactional
    public Goal saveGoal(PlanWriteRequestDto planWriteRequestDto, Member member) {
        Goal goal = new Goal(planWriteRequestDto, member);

        return goalRepository.save(goal);
    }
}
