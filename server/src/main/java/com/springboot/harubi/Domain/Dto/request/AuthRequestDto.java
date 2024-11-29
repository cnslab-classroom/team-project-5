package com.springboot.harubi.Domain.Dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequestDto {
    private String name;
    private String sign_id;
    private String password;
    private String nickname;
    private String email;
    private boolean agreed;
}
