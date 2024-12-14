package com.springboot.harubi.Domain.Dto.response;

import com.springboot.harubi.Domain.Entity.Member;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;

@Data
@AllArgsConstructor
public class ProfileResponseDto {
    private String name;
    private Integer status;
    private String icon;
    private String bio;
    private String affiliation;
    // 스트릭 정보를 설정하는 메서드 추가
    @Setter
    private StreakResponseDto streak; // 스트릭 정보 추가

    // Member 엔티티로부터 ProfileResponseDto 생성
    public static ProfileResponseDto fromEntity(Member member) {
        return new ProfileResponseDto(
                member.getName(),
                member.getStatus(),
                member.getIcon(),
                member.getBio(),
                member.getAffiliation(),
                null // 기본값으로 null 설정, 스트릭 정보는 이후에 설정
        );
    }

}
