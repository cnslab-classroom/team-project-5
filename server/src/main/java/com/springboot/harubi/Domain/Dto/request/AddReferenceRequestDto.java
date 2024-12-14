package com.springboot.harubi.Domain.Dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddReferenceRequestDto {
    private String reference_name; // Reference 이름
    private String reference_url; // Reference URL
}
