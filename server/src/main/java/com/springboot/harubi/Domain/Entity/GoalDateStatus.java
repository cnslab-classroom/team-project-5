package com.springboot.harubi.Domain.Entity;

import com.springboot.harubi.Domain.Dto.request.PlanCheckRequestDto;
import com.springboot.harubi.Domain.Dto.response.PlanCheckResponseDto;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name = "goal_date_status")
public class GoalDateStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long status_id;

    @Temporal(TemporalType.DATE) // 시간 정보 없이 날짜만 저장
    @Column(nullable = false)
    private Date goal_date;

    @Column(nullable = false)
    private boolean goal_status = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "goal_id")
    private Goal goal;

    public GoalDateStatus(Date goal_date, boolean goal_status, Goal goal) {
        this.goal_date = goal_date;
        this.goal_status = goal_status;
        this.goal = goal;
    }

//    public GoalDateStatus(PlanCheckRequestDto planCheckRequestDto, Goal goal) {
//        this.status_id = planCheckRequestDto.getPlan_status_id();
//        this.goal_status = planCheckRequestDto.isPlan_status();
//        this.goal = goal;
//    }

    public Member getMember() {
        return this.goal.getMember();
    }
}
