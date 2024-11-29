package com.springboot.harubi.Service;

import com.springboot.harubi.Repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    // 예시 코드 - 회원가입
    //    @Transactional
    //    public void join(AuthRequestDto authRequestDto) {
    //        validateAuthReqeust(authRequestDto);
    //        validateDuplicateEmail(authRequestDto.getEmail());
    //        memberRepository.save(convertToMember(authRequestDto));
    //    }
}
