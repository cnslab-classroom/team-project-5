package com.springboot.harubi.Domain.Dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupListResponseDto {
    private List<GroupInfo> study_group;  // 스터디 그룹 이름 목록

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class GroupInfo {
        private String study_group_name;
        private String study_emoji;
    }
}
