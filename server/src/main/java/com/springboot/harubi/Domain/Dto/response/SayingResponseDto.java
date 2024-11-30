package com.springboot.harubi.Domain.Dto.response;

import com.springboot.harubi.Domain.Entity.Saying;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SayingResponseDto {
    private String saying_text;
    private String saying_speaker;

    public static SayingResponseDto fromEntity(Saying saying) {
        return new SayingResponseDto(
                saying.getSaying_text(),
                saying.getSaying_speaker()
        );
    }
}
