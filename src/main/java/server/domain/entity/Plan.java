package server.domain.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "plans") // 테이블 이름 설정
public class Plan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본 키 자동 생성
    private Long plan_id;

    @Column(nullable = false)
    private String plan_text;

    @Column(nullable = false)
    private LocalDateTime goal_date;

    // Many-to-One 관계: Plan과 Member 간의 관계 설정
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false) // 외래 키 설정
    private Member member;

    // 기본 생성자
    public Plan() {}

    // 모든 필드를 포함하는 생성자
    public Plan(String plan_text, LocalDateTime goal_date, Member member) {
        this.plan_text = plan_text;
        this.goal_date = goal_date;
        this.member = member;
    }

    // Getter & Setter 메서드
    public Long getPlan_id() {
        return plan_id;
    }

    public void setPlan_id(Long plan_id) {
        this.plan_id = plan_id;
    }

    public String getPlan_text() {
        return plan_text;
    }

    public void setPlan_text(String plan_text) {
        this.plan_text = plan_text;
    }

    public LocalDateTime getGoal_date() {
        return goal_date;
    }

    public void setGoal_date(LocalDateTime goal_date) {
        this.goal_date = goal_date;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }
}
