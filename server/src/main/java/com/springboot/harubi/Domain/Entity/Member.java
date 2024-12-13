package com.springboot.harubi.Domain.Entity;

import com.springboot.harubi.Domain.Dto.request.AuthRequestDto;
import com.springboot.harubi.Exception.BaseException;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long member_id;

    @Column (nullable = false)
    private String name;

    @Column (nullable = false)
    private String signId;

    @Column (nullable = false)
    private String password;

    @Column (nullable = false)
    private String nickname;

    @Column (nullable = false)
    private String email;

    @Column (nullable = false)
    private boolean agreed;

    @Column
    private Integer status;

    @Column
    private String icon;

    @Column
    private String bio;

    @Column
    private String affiliation;

    // 다대다 관계 설정
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<MemberGroup> memberGroups = new ArrayList<>();

    // One to Many 관계 설정
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Goal> goals = new ArrayList<>();

    // One to Many 관계 설정
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Plan> plans = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Star> stars;  // 멤버가 여러 그룹을 즐겨찾기할 수 있음

    public Member(AuthRequestDto authRequestDto) {
        this.name = authRequestDto.getName();
        this.signId = authRequestDto.getSign_id();
        this.password = authRequestDto.getPassword();
        this.nickname = authRequestDto.getNickname();
        this.email = authRequestDto.getEmail();
        this.agreed = authRequestDto.isAgreed();
    }

    public void validatePassword(String password) {
        if (!password.equals(this.password)) {
            throw new BaseException(HttpStatus.UNAUTHORIZED.value(), "비밀번호 일치하지 않음");
        }
    }
}
