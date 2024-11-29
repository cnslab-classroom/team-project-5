package com.springboot.harubi.Domain.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Reference {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reference_id;

    @Column(nullable = false)
    private String reference_link;

    // Many to One 관계 설정
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id")  // 외래 키로 Member의 기본 키 참조
    private StudyGroup studyGroup;
}
