package server.domain.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "members") // 테이블 이름 설정
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본 키 자동 생성
    private Long member_id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String sign_id;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private boolean agreed;

    @Column
    private Integer status;

    @Column
    private String icon;

    @Column
    private String bio;

    @Column
    private String affiliation;

    // Many-to-One 관계: Member와 MemberGroup
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MemberGroup> memberGroups = new ArrayList<>();

    // One-to-Many 관계: Member와 Goal
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Goal> goals = new ArrayList<>();

    // One-to-Many 관계: Member와 Plan
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Plan> plans = new ArrayList<>();

    // One-to-Many 관계: Member와 Star
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Star> stars = new ArrayList<>();

    // 기본 생성자
    public Member() {}

    // Getter & Setter 메서드
    public Long getMember_id() {
        return member_id;
    }

    public void setMember_id(Long member_id) {
        this.member_id = member_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSign_id() {
        return sign_id;
    }

    public void setSign_id(String sign_id) {
        this.sign_id = sign_id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isAgreed() {
        return agreed;
    }

    public void setAgreed(boolean agreed) {
        this.agreed = agreed;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getAffiliation() {
        return affiliation;
    }

    public void setAffiliation(String affiliation) {
        this.affiliation = affiliation;
    }

    public List<MemberGroup> getMemberGroups() {
        return memberGroups;
    }

    public void setMemberGroups(List<MemberGroup> memberGroups) {
        this.memberGroups = memberGroups;
    }

    public List<Goal> getGoals() {
        return goals;
    }

    public void setGoals(List<Goal> goals) {
        this.goals = goals;
    }

    public void addGoal(Goal goal) {
        this.goals.add(goal);
        goal.setMember(this);
    }

    public void removeGoal(Goal goal) {
        this.goals.remove(goal);
        goal.setMember(null);
    }

    public List<Plan> getPlans() {
        return plans;
    }

    public void setPlans(List<Plan> plans) {
        this.plans = plans;
    }

    public void addPlan(Plan plan) {
        this.plans.add(plan);
        plan.setMember(this);
    }

    public void removePlan(Plan plan) {
        this.plans.remove(plan);
        plan.setMember(null);
    }

    public List<Star> getStars() {
        return stars;
    }

    public void setStars(List<Star> stars) {
        this.stars = stars;
    }

    public void addStar(Star star) {
        this.stars.add(star); // stars 리스트에 추가
        star.setMember(this); // Star 객체의 member 필드 동기화
    }

    public void removeStar(Star star) {
        this.stars.remove(star); // stars 리스트에서 제거
        star.setMember(null); // Star 객체의 member 필드 초기화
    }
}
