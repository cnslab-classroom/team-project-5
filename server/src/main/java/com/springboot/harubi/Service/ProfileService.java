package com.springboot.harubi.Service;

import com.springboot.harubi.Domain.Dto.request.ProfileUpdateRequestDto;
import com.springboot.harubi.Domain.Dto.response.ProfileResponseDto;
import com.springboot.harubi.Domain.Dto.response.UserProfileResponseDto;
import com.springboot.harubi.Domain.Entity.Member;
import com.springboot.harubi.Repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional
    public void updateProfile(Long memberId, ProfileUpdateRequestDto requestDto) {
        // 데이터베이스에서 Member 조회
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("해당 ID의 멤버를 찾을 수 없습니다."));

        // 요청에서 넘어온 값만 업데이트, null이면 기존 값 유지
        member.setName(requestDto.getName() != null ? requestDto.getName() : member.getName());
        member.setBio(requestDto.getBio() != null ? requestDto.getBio() : member.getBio());
        member.setAffiliation(requestDto.getAffiliation() != null ? requestDto.getAffiliation() : member.getAffiliation());
        member.setIcon(requestDto.getIcon() != null ? requestDto.getIcon() : member.getIcon());

        // 수정된 엔티티 저장 (Transactional로 인해 자동 저장)
        memberRepository.save(member);
    }

}
