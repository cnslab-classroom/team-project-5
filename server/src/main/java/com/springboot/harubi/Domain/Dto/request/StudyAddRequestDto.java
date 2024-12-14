package com.springboot.harubi.Domain.Dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudyAddRequestDto {
    private String study_name;
    private Date study_end_date;
    private String study_emoji;
}