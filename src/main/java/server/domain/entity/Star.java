package server.domain.entity;

import javax.persistence.*;

@Entity
@Table(name = "stars") // 테이블 이름 설정
public class Star {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본 키 자동 생성
    private Long star_id;

    @Column(nullable = false)
    private boolean star; // 즐겨찾기 여부를 boolean으로 관리

    @ManyToOne(fetch = FetchType.LAZY) // Member와 다대일 관계
    @JoinColumn(name = "member_id", nullable = false) // 외래 키 설정
    private Member member;

    @OneToOne(fetch = FetchType.LAZY) // Group과 일대일 관계
    @JoinColumn(name = "group_id", nullable = false) // 외래 키 설정
    private Group group;

    // 기본 생성자
    public Star() {}

    // 모든 필드를 포함하는 생성자
    public Star(boolean star, Member member, Group group) {
        this.star = star;
        this.member = member;
        this.group = group;
    }

    // Getter & Setter 메서드
    public Long getStar_id() {
        return star_id;
    }

    public void setStar_id(Long star_id) {
        this.star_id = star_id;
    }

    public boolean isStar() {
        return star;
    }

    public void setStar(boolean star) {
        this.star = star;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
}
