package com.springboot.harubi.Domain.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Star {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long star_id;  // 필요시 ID 추가 (없어도 무방할 수 있음)

    @Column(nullable = false)
    private boolean star;  // 즐겨찾기 여부를 boolean으로 관리

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "member_id", nullable = false)
    private Member member;

    @OneToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id", nullable = false)
    private StudyGroup studyGroup;  // 그룹과 1:1 관계
}
