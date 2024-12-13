package com.springboot.harubi.Service;

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
    public void join(AuthRequestDto authRequestDto) {
        validateAuthReqeust(authRequestDto);
        validateDuplicateEmail(authRequestDto.getEmail());
        memberRepository.save(convertToMember(authRequestDto));
    }

    private void validateAuthReqeust(AuthRequestDto authRequestDto) {
        if (authRequestDto.getName() == null) {
            throw new BaseException(400, "이름을 입력해 주세요.");
        }
        if (authRequestDto.getSign_id() == null) {
            throw new BaseException(400, "아이디를 입력해 주세요.");
        }
        if (authRequestDto.getPassword() == null) {
            throw new BaseException(400, "비밀번호를 입력해 주세요.");
        }
        if (authRequestDto.getNickname() == null) {
            throw new BaseException(400, "닉네임을 입력해 주세요.");
        }
        if (authRequestDto.getEmail() == null) {
            throw new BaseException(400, "이메일을 입력해 주세요.");
        }
        if (!authRequestDto.isAgreed()) {
            throw new BaseException(400, "개인정보 수집에 동의해 주세요.");
        }
    }

    // 중복된 이메일 체크
    private void validateDuplicateEmail(String email) {
        if (!memberRepository.findByEmail(email).stream().toList().isEmpty()) {
            throw new BaseException(400, "이미 회원가입된 이메일");
        }
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
