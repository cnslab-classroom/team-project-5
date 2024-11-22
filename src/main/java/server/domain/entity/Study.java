package server.domain.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "studies") // 테이블 이름 설정
public class Study {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본 키 자동 생성
    private Long study_id;

    @Column(nullable = false)
    private String study_text;

    @Column(nullable = false)
    private Date study_start_date;

    @Column(nullable = false)
    private Date study_end_date;

    // Many-to-One 관계: Study와 Group 간의 관계 설정
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id", nullable = false) // 외래 키 설정
    private Group group;

    // 기본 생성자
    public Study() {}

    // 모든 필드를 포함하는 생성자
    public Study(String study_text, Date study_start_date, Date study_end_date, Group group) {
        this.study_text = study_text;
        this.study_start_date = study_start_date;
        this.study_end_date = study_end_date;
        this.group = group;
    }

    // Getter & Setter 메서드
    public Long getStudy_id() {
        return study_id;
    }

    public void setStudy_id(Long study_id) {
        this.study_id = study_id;
    }

    public String getStudy_text() {
        return study_text;
    }

    public void setStudy_text(String study_text) {
        this.study_text = study_text;
    }

    public Date getStudy_start_date() {
        return study_start_date;
    }

    public void setStudy_start_date(Date study_start_date) {
        this.study_start_date = study_start_date;
    }

    public Date getStudy_end_date() {
        return study_end_date;
    }

    public void setStudy_end_date(Date study_end_date) {
        this.study_end_date = study_end_date;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
}
