package com.springboot.harubi.Domain.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@Entity
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

    public GoalDateStatus(Date goal_date, boolean goal_status, Goal goal) {
        this.goal_date = goal_date;
        this.goal_status = goal_status;
        this.goal = goal;
    }
}
