package server.domain.entity;

import javax.persistence.*;

@Entity
@Table(name = "sayings") // 테이블 이름 설정
public class Saying {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본 키 자동 생성
    private Long saying_id;

    @Column(nullable = false)
    private String saying_text;

    @Column
    private String saying_speaker;

    // 기본 생성자
    public Saying() {}

    // 모든 필드를 포함하는 생성자
    public Saying(String saying_text, String saying_speaker) {
        this.saying_text = saying_text;
        this.saying_speaker = saying_speaker;
    }

    // Getter & Setter
    public Long getSaying_id() {
        return saying_id;
    }

    public void setSaying_id(Long saying_id) {
        this.saying_id = saying_id;
    }

    public String getSaying_text() {
        return saying_text;
    }

    public void setSaying_text(String saying_text) {
        this.saying_text = saying_text;
    }

    public String getSaying_speaker() {
        return saying_speaker;
    }

    public void setSaying_speaker(String saying_speaker) {
        this.saying_speaker = saying_speaker;
    }
}
