package com.springboot.harubi.Domain.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

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

    @Column (nullable = false)
    private Date goal_date;

    @Column (nullable = false)
    private boolean goal_status;

    // Many to One 관계 설정
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")  // 외래 키로 Member의 기본 키 참조
    private Member member;
}
