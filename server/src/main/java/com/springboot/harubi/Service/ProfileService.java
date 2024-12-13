package com.springboot.harubi.Service;

import com.springboot.harubi.Domain.Dto.request.ProfileUpdateRequestDto;
import com.springboot.harubi.Domain.Dto.response.ProfileResponseDto;
import com.springboot.harubi.Domain.Dto.response.UserProfileResponseDto;
import com.springboot.harubi.Domain.Entity.Member;
import com.springboot.harubi.Repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileService {
    private final MemberRepository memberRepository;

    // 프로필 조회
    public ProfileResponseDto getProfile(Long memberId) {
        // MemberRepository에서 memberId로 데이터 조회
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("Member not found"));

        // 기존 ProfileResponseDto의 생성 로직을 사용하여 반환
        return ProfileResponseDto.fromEntity(member);
    }

    // 프로필 수정
    public void updateProfile(Long memberId, ProfileUpdateRequestDto request) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("회원 정보를 찾을 수 없습니다."));

        member.setName(request.getName());
        member.setIcon(request.getIcon());
        member.setStatus(request.getStatus());
        member.setBio(request.getBio());
        member.setAffiliation(request.getAffiliation());

        memberRepository.save(member);
    }
}
