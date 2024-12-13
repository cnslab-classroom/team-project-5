package com.springboot.harubi.Service;

import com.springboot.harubi.Domain.Dto.response.GroupListResponseDto;
import com.springboot.harubi.Domain.Entity.Member;
import com.springboot.harubi.Exception.BaseException;
import com.springboot.harubi.Repository.MemberRepository;
import com.springboot.harubi.Repository.StudyGroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class GroupService {
    private final StudyGroupRepository studyGroupRepository;
    private final MemberRepository memberRepository;

    public GroupListResponseDto getGroupList(Long member_id) {
        Member member = memberRepository.findById(member_id)
                .orElseThrow(() -> new BaseException(404, "존재하지 않는 회원입니다."));

        List<String> groupNames = member.getMemberGroups().stream()
                .map(memberGroup -> memberGroup.getStudyGroup().getGroup_name())
                .toList();

        return new GroupListResponseDto(groupNames);
    }
}
