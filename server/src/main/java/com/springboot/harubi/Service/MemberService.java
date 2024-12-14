package com.springboot.harubi.Service;

import com.springboot.harubi.Domain.Dto.request.AgreeRequestDto;
import com.springboot.harubi.Domain.Dto.request.AuthRequestDto;
import com.springboot.harubi.Domain.Dto.response.AuthLoginResponseDto;
import com.springboot.harubi.Domain.Entity.Member;
import com.springboot.harubi.Exception.BaseException;
import com.springboot.harubi.Repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    @Transactional
    public void tempSave(AuthRequestDto authRequestDto) {
        if (memberRepository.existsByEmail(authRequestDto.getEmail())) {
            throw new BaseException(400, "이미 가입된 이메일입니다.");
        }
        Member member = convertToMember(authRequestDto);
        member.setAgreed(false);
        memberRepository.save(member);
    }

    @Transactional
    public void completeSignUp(AgreeRequestDto agreeRequestDto) {
        Member member = memberRepository.findByEmail(agreeRequestDto.getEmail())
                .orElseThrow(() -> new BaseException(404, "회원 정보가 존재하지 않습니다."));

        if (!agreeRequestDto.isAgreed()) {
            throw new BaseException(400, "개인정보 처리 방침에 동의해야 합니다.");
        }

        member.setAgreed(true);
        memberRepository.save(member);
    }

    private Member convertToMember(AuthRequestDto authRequestDto) {
        return new Member(authRequestDto, "😆");
    }

    @Transactional
    public AuthLoginResponseDto login(String sign_id, String password) {
        Member member = getMemberById(sign_id);
        member.validatePassword(password);
        return new AuthLoginResponseDto(member.getMember_id());
    }

    private Member getMemberById(String sign_id) {
        return memberRepository.findBySignId(sign_id).stream().findFirst()
                .orElseThrow(()->new BaseException(404, "회원가입 되지 않은 아이디"));
    }
}
