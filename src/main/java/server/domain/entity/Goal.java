package server.domain.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "goals") // 테이블 이름 설정
public class Goal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본 키 자동 생성
    private Long goal_id;

    @Column(nullable = false)
    private String goal_text;

    @Column(nullable = false)
    private Date goal_start_date;

    @Column(nullable = false)
    private Date goal_end_date;

    @Column(nullable = false)
    private Date goal_date;

    @Column(nullable = false)
    private boolean goal_status;

    @ManyToOne(fetch = FetchType.LAZY) // Many-to-One 관계 설정
    @JoinColumn(name = "member_id") // 외래 키 이름 설정
    private Member member;

    // 기본 생성자
    public Goal() {}

    // Getter & Setter
    public Long getGoal_id() {
        return goal_id;
    }

    public void setGoal_id(Long goal_id) {
        this.goal_id = goal_id;
    }

    public String getGoal_text() {
        return goal_text;
    }

    public void setGoal_text(String goal_text) {
        this.goal_text = goal_text;
    }

    public Date getGoal_start_date() {
        return goal_start_date;
    }

    public void setGoal_start_date(Date goal_start_date) {
        this.goal_start_date = goal_start_date;
    }

    public Date getGoal_end_date() {
        return goal_end_date;
    }

    public void setGoal_end_date(Date goal_end_date) {
        this.goal_end_date = goal_end_date;
    }

    public Date getGoal_date() {
        return goal_date;
    }

    public void setGoal_date(Date goal_date) {
        this.goal_date = goal_date;
    }

    public boolean isGoal_status() {
        return goal_status;
    }

    public void setGoal_status(boolean goal_status) {
        this.goal_status = goal_status;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }
}
