package com.springboot.harubi.Service;

import com.springboot.harubi.Domain.Dto.request.MakeGroupRequestDto;
import com.springboot.harubi.Domain.Dto.response.GroupListResponseDto;
import com.springboot.harubi.Domain.Dto.response.MakeGroupResponseDto;
import com.springboot.harubi.Domain.Entity.Member;
import com.springboot.harubi.Domain.Entity.MemberGroup;
import com.springboot.harubi.Domain.Entity.Study;
import com.springboot.harubi.Domain.Entity.StudyGroup;
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

    @Transactional
    public MakeGroupResponseDto makesGroup(Long member_id, MakeGroupRequestDto makeGroupRequestDto) {
        Member member = memberRepository.findById(member_id)
                .orElseThrow(() -> new BaseException(404, "존재하지 않는 회원입니다."));

        StudyGroup group = saveStudyGroup(makeGroupRequestDto, member);

        saveMemberGroup(member, group);

        return new MakeGroupResponseDto(group.getGroup_id());
    }

    @Transactional
    public StudyGroup saveStudyGroup(MakeGroupRequestDto makeGroupRequestDto, Member member) {
        StudyGroup group = new StudyGroup();
        group.setGroup_name(makeGroupRequestDto.getStudy_group_name());
        group.setGroup_emoji(makeGroupRequestDto.getStudy_emoji());

        return studyGroupRepository.save(group);
    }

    @Transactional
    public void saveMemberGroup(Member member, StudyGroup group) {
        MemberGroup memberGroup = new MemberGroup();
        memberGroup.setMember(member);
        memberGroup.setStudyGroup(group);

        member.getMemberGroups().add(memberGroup);
        group.getMemberGroups().add(memberGroup);
    }

    public GroupListResponseDto getGroupList(Long member_id) {
        Member member = memberRepository.findById(member_id)
                .orElseThrow(() -> new BaseException(404, "존재하지 않는 회원입니다."));

        List<GroupListResponseDto.GroupInfo> groupInfoList = member.getMemberGroups().stream()
                .map(memberGroup -> new GroupListResponseDto.GroupInfo(
                        memberGroup.getStudyGroup().getGroup_name(),
                        memberGroup.getStudyGroup().getGroup_emoji()
                ))
                .toList();

        return new GroupListResponseDto(groupInfoList);
    }
}
