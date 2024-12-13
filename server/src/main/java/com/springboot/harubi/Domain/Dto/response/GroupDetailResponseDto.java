package com.springboot.harubi.Domain.Dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupDetailResponseDto {
    private List<StudyInfo> study;
    private List<MemberInfo> members;
    private List<ReferenceLink> reference_links;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class StudyInfo {
        private String study_end_date;
        private String study_text;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class MemberInfo {
        private String emoji;
        private String name;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ReferenceLink {
        private String name;
        private String url;
    }
}
