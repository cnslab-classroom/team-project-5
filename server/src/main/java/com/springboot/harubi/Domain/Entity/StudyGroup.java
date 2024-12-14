package com.springboot.harubi.Domain.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "study_group")
public class StudyGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long group_id;

    @Column(nullable = false)
    private String group_name;

    @Column
    private String group_emoji;

    // 다대다 관계 설정
    @OneToMany(mappedBy = "studyGroup", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<MemberGroup> memberGroups = new ArrayList<>();

    // One to Many 관계 설정
    @OneToMany(mappedBy = "studyGroup", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Study> studies = new ArrayList<>();

    // One to Many 관계 설정
    @OneToMany(mappedBy = "studyGroup", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reference> references = new ArrayList<>();
}
