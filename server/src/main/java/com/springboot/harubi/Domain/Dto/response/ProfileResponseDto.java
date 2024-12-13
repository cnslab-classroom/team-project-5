package com.springboot.harubi.Domain.Dto.response;

import com.springboot.harubi.Domain.Entity.Member;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProfileResponseDto {
    private String name;
    private Integer status;
    private String icon;
    private String bio;
    private String affiliation;

    public static ProfileResponseDto fromEntity(Member member) {
        return new ProfileResponseDto(
                member.getName(),
                member.getStatus(),
                member.getIcon(),
                member.getBio(),
                member.getAffiliation()
        );
    }
}
