package com.springboot.harubi.Service;

import com.springboot.harubi.Domain.Dto.response.UserProfileResponseDto;
import com.springboot.harubi.Domain.Entity.Member;
import com.springboot.harubi.Repository.MemberRepository;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {

    private final MemberRepository memberRepository;

    public ProfileService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public UserProfileResponseDto getProfileByMemberId(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("Member not found with id: " + memberId));

        return new UserProfileResponseDto(
                member.getName(),
                member.getStatus() == 1 ? "active" : "inactive",
                member.getIcon(),
                member.getBio(),
                member.getAffiliation()
        );
    }
}

