package com.springboot.harubi.Domain.Dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthLoginRequestDto {
    private String sign_id;
    private String password;
}
