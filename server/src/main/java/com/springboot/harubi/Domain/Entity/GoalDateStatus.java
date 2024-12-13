package com.springboot.harubi.Domain.Entity;

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

    @Column(nullable = false)
    private Date goal_date;

    @Column(nullable = false)
    private boolean goal_status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "goal_id")
    private Goal goal;

    @ManyToOne(fetch = FetchType.LAZY) // Member와의 관계 매핑
    @JoinColumn(name = "member_id", nullable = false) // 실제 DB의 member_id와 매핑
    private Member member;

    public GoalDateStatus(Date goal_date, boolean goal_status, Goal goal, Member member) {
        this.goal_date = goal_date;
        this.goal_status = goal_status;
        this.goal = goal;
        this.member = member;
    }

    public GoalDateStatus(Date time, boolean b, Goal goal) {
    }
}
