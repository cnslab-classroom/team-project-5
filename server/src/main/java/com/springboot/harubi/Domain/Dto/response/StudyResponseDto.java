package com.springboot.harubi.Domain.Dto.response;

import com.springboot.harubi.Domain.Entity.Study;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.text.SimpleDateFormat;

@Getter
@AllArgsConstructor
public class StudyResponseDto {
    private String study_text;// 지정된 날짜 형식으로 반환
    private String study_end_date;

    public static StudyResponseDto fromEntity(Study study) {
        // 날짜 포맷 정의
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        // Study 엔티티의 필드 값 변환 및 반환
        return new StudyResponseDto(
                study.getStudy_text(),
                dateFormat.format(study.getStudy_end_date()) // 포맷된 종료 날짜
        );
    }
}
