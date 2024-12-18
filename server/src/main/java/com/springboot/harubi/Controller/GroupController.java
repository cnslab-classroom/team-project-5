package com.springboot.harubi.Controller;

import com.springboot.harubi.Common.BaseResponse;
import com.springboot.harubi.Domain.Dto.request.AddReferenceRequestDto;
import com.springboot.harubi.Domain.Dto.request.MakeGroupRequestDto;
import com.springboot.harubi.Domain.Dto.request.MemberInviteRequestDto;
import com.springboot.harubi.Domain.Dto.request.StudyAddRequestDto;
import com.springboot.harubi.Domain.Dto.response.AddReferenceResponseDto;
import com.springboot.harubi.Domain.Dto.response.GroupDetailResponseDto;
import com.springboot.harubi.Domain.Dto.response.GroupListResponseDto;
import com.springboot.harubi.Domain.Dto.response.MakeGroupResponseDto;
import com.springboot.harubi.Domain.Dto.response.StudyAddResponseDto;
import com.springboot.harubi.Service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/group")
@RequiredArgsConstructor
public class GroupController {
    private final GroupService groupService;
    @PostMapping("/{member_id}/list")
    public BaseResponse<MakeGroupResponseDto> makeGroup(@PathVariable("member_id") Long member_id,
            @RequestBody MakeGroupRequestDto makeGroupRequestDto) {
        MakeGroupResponseDto response = groupService.makesGroup(member_id, makeGroupRequestDto);
        return new BaseResponse<>(HttpStatus.OK.value(), "스터디 그룹이 추가되었습니다", response);
    }

    @GetMapping("/{member_id}/list")
    public BaseResponse<GroupListResponseDto> showGroupList(@PathVariable("member_id") Long member_id) {
        GroupListResponseDto response = groupService.getGroupList(member_id);
        return new BaseResponse<>(HttpStatus.OK.value(), null, response);
    }

    @GetMapping("/{group_id}/detail")
    public BaseResponse<GroupDetailResponseDto> showGroupDetail(@PathVariable("group_id") Long group_id) {
        GroupDetailResponseDto response = groupService.getGroupDetails(group_id);
        return new BaseResponse<>(HttpStatus.OK.value(), null, response);
    }

    @PostMapping("/{group_id}/add_study")
    public BaseResponse<StudyAddResponseDto> makeStudyPlan(@PathVariable("group_id") Long group_id,
            @RequestBody StudyAddRequestDto studyAddRequestDto) {
        StudyAddResponseDto response = groupService.makePlan(group_id, studyAddRequestDto);
        return new BaseResponse<>(HttpStatus.OK.value(), "스터디 그룹 목표가 추가되었습니다", response);
    }

    @PostMapping("/{group_id}/add_reference")
    public BaseResponse<AddReferenceResponseDto> addReference(
            @PathVariable Long group_id,
            @RequestBody AddReferenceRequestDto requestDto) {

        AddReferenceResponseDto responseDto = groupService.addReference(group_id, requestDto);
        return new BaseResponse<>(HttpStatus.OK.value(), "레퍼런스가 추가되었습니다.", responseDto);
    }

    @PostMapping("/{group_id}/invite")
    public BaseResponse inviteMember(@PathVariable("group_id") Long group_id,
            @RequestBody MemberInviteRequestDto memberInviteRequestDto) {
        groupService.invitesMember(group_id, memberInviteRequestDto);
        return new BaseResponse<>(HttpStatus.OK.value(), "스터디 그룹 멤버가 추가되었습니다", null);
    }
}
