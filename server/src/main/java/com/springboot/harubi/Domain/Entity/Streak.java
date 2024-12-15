package com.springboot.harubi.Domain.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Streak {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long streak_id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Column(nullable = false)
    private int days = 0;

    public Streak(Member member, int days) {
        this.member = member;
        this.days = days;
    }

    public void incrementDays() {
        this.days++;
    }

    public void resetDays() {
        this.days = 0;
    }
}

