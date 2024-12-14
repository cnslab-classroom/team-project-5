package com.springboot.harubi.Controller;

import com.springboot.harubi.Common.BaseResponse;
import com.springboot.harubi.Domain.Dto.request.AgreeRequestDto;
import com.springboot.harubi.Domain.Dto.request.AuthLoginRequestDto;
import com.springboot.harubi.Domain.Dto.request.AuthRequestDto;
import com.springboot.harubi.Domain.Dto.response.AuthLoginResponseDto;
import com.springboot.harubi.Exception.BaseException;
import com.springboot.harubi.Service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/auth/signUp")
    public BaseResponse signUp(@RequestBody AuthRequestDto authRequestDto) {
        memberService.tempSave(authRequestDto);
        return new BaseResponse(HttpStatus.OK.value(), null);
    }

    @PostMapping("/auth/login")
    public BaseResponse login(@RequestBody AuthLoginRequestDto authLoginRequestDto) {
        AuthLoginResponseDto response = memberService.login(authLoginRequestDto.getSign_id(), authLoginRequestDto.getPassword());
        return new BaseResponse(HttpStatus.OK.value(), "로그인 성공", response);
    }

    @PostMapping("/auth/agree")
    public BaseResponse agreeToPrivacy(@RequestBody AgreeRequestDto agreeRequestDto) {
        memberService.completeSignUp(agreeRequestDto);
        return new BaseResponse(HttpStatus.OK.value(), "회원가입이 완료되었습니다.");
    }
}
