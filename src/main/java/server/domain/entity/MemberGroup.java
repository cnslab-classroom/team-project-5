package server.domain.entity;

import javax.persistence.*;

@Entity
@Table(name = "member_groups") // 테이블 이름 설정
public class MemberGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본 키 자동 생성
    private Long memberGroup_id;

    @ManyToOne(fetch = FetchType.LAZY) // Member와 다대일 관계
    @JoinColumn(name = "member_id", nullable = false) // 외래 키 설정
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY) // Group과 다대일 관계
    @JoinColumn(name = "group_id", nullable = false) // 외래 키 설정
    private Group group;

    // 기본 생성자
    public MemberGroup() {}

    // 모든 필드를 포함하는 생성자
    public MemberGroup(Member member, Group group) {
        this.member = member;
        this.group = group;
    }

    // Getter & Setter
    public Long getMemberGroup_id() {
        return memberGroup_id;
    }

    public void setMemberGroup_id(Long memberGroup_id) {
        this.memberGroup_id = memberGroup_id;
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
