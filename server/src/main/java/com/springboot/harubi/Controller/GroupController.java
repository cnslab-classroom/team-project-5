package com.springboot.harubi.Controller;

import com.springboot.harubi.Common.BaseResponse;
import com.springboot.harubi.Domain.Dto.response.GroupListResponseDto;
import com.springboot.harubi.Service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/group")
@RequiredArgsConstructor
public class GroupController {
    private final GroupService groupService;

    @GetMapping("/{member_id}/list")
    public BaseResponse<GroupListResponseDto> showGroupList(@PathVariable("member_id") Long member_id) {
        GroupListResponseDto response = groupService.getGroupList(member_id);
        return new BaseResponse<>(HttpStatus.OK.value(), null, response);
    }
}
