package com.springboot.harubi.Domain.Entity;

import com.springboot.harubi.Domain.Dto.request.PlanWriteRequestDto;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Goal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long goal_id;

    @Column(nullable = false)
    private String goal_text;

    @Column (nullable = false)
    private Date goal_start_date;

    @Column (nullable = false)
    private Date goal_end_date;

    @OneToMany(mappedBy = "goal", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<GoalDateStatus> goalDateStatuses = new ArrayList<>();

    // Many to One 관계 설정
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")  // 외래 키로 Member의 기본 키 참조
    private Member member;

    @PrePersist
    public void prePersist() {
        // goal_start_date 부터 goal_end_date 까지 목표 날짜 상태 추가
        if (goal_start_date != null && goal_end_date != null) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(goal_start_date);

            while (!cal.getTime().after(goal_end_date)) {
                Date currentDate = cal.getTime();
                GoalDateStatus status = new GoalDateStatus(currentDate, false, this);
                goalDateStatuses.add(status);
                cal.add(Calendar.DATE, 1);  // 하루씩 증가
            }
        }
    }

    public Goal(PlanWriteRequestDto planWriteRequestDto, Member member) {
        this.goal_text = planWriteRequestDto.getGoal_text();
        this.goal_start_date = planWriteRequestDto.getGoal_start_date();
        this.goal_end_date = planWriteRequestDto.getGoal_end_date();
        this.member = member;
    }
}
