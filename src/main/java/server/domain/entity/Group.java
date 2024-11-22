package server.domain.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "groups") // 테이블 이름 설정
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본 키 자동 생성
    private Long group_id;

    @Column(nullable = false)
    private String group_name;

    // Many-to-One 관계: Member와 Group 간의 관계를 정의
    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MemberGroup> memberGroups = new ArrayList<>();

    // One-to-Many 관계: Group과 Study 간의 관계를 정의
    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Study> studies = new ArrayList<>();

    // One-to-Many 관계: Group과 Reference 간의 관계를 정의
    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reference> references = new ArrayList<>();

    // One-to-One 관계: Group과 Star
    @OneToOne(mappedBy = "group", cascade = CascadeType.ALL, orphanRemoval = true)
    private Star star;

    // 기본 생성자
    public Group() {}

    // Getter and Setter methods
    public Long getGroup_id() {
        return group_id;
    }

    public void setGroup_id(Long group_id) {
        this.group_id = group_id;
    }

    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }

    public List<MemberGroup> getMemberGroups() {
        return memberGroups;
    }

    public void setMemberGroups(List<MemberGroup> memberGroups) {
        this.memberGroups = memberGroups;
    }

    public List<Study> getStudies() {
        return studies;
    }

    public void setStudies(List<Study> studies) {
        this.studies = studies;
    }

    public List<Reference> getReferences() {
        return references;
    }

    public void setReferences(List<Reference> references) {
        this.references = references;
    }

    public Star getStar() {
        return star;
    }

    public void setStar(Star star) {
        this.star = star;
    }
}
