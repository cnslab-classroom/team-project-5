package server.domain.entity;

import javax.persistence.*;

@Entity
@Table(name = "references") // 테이블 이름 설정
public class Reference {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본 키 자동 생성
    private Long reference_id;

    @Column(nullable = false)
    private String reference_link;

    // Many-to-One 관계: Reference와 Group 간의 관계 설정
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id", nullable = false) // 외래 키 설정
    private Group group;

    // 기본 생성자
    public Reference() {}

    // 모든 필드를 포함하는 생성자
    public Reference(String reference_link, Group group) {
        this.reference_link = reference_link;
        this.group = group;
    }

    // Getter & Setter 메서드
    public Long getReference_id() {
        return reference_id;
    }

    public void setReference_id(Long reference_id) {
        this.reference_id = reference_id;
    }

    public String getReference_link() {
        return reference_link;
    }

    public void setReference_link(String reference_link) {
        this.reference_link = reference_link;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
}
