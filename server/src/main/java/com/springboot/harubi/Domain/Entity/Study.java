package com.springboot.harubi.Domain.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@Entity
public class Study {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long study_id;

    @Column(nullable = false)
    private String study_text;

    @Column (nullable = false)
    private Date study_start_date;

    @Column (nullable = false)
    private Date study_end_date;

    // Many to One 관계 설정
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id")  // 외래 키로 Member의 기본 키 참조
    private StudyGroup studyGroup;
}
