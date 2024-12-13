package com.springboot.harubi.Domain.Dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MakeGroupRequestDto {
    private String study_group_name;
    private String study_emoji;
}
