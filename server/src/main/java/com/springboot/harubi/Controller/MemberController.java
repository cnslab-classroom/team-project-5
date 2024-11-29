package com.springboot.harubi.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {

    // 예시 코드 - 회원가입
    //    @PostMapping("/auth/signup")
    //    public BaseResponse signup(@RequestBody AuthRequestDto authRequestDto) {
    //        memberService.join(authRequestDto);
    //        return new BaseResponse(HttpStatus.OK.value(), "회원가입이 완료되었습니다.");
    //    }
}
