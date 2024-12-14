package com.springboot.harubi.Service;

import com.springboot.harubi.Domain.Dto.request.AddReferenceRequestDto;
import com.springboot.harubi.Domain.Dto.request.MakeGroupRequestDto;
import com.springboot.harubi.Domain.Dto.request.StudyAddRequestDto;
import com.springboot.harubi.Domain.Dto.response.GroupDetailResponseDto;
import com.springboot.harubi.Domain.Dto.response.GroupListResponseDto;
import com.springboot.harubi.Domain.Dto.response.MakeGroupResponseDto;
import com.springboot.harubi.Domain.Dto.response.StudyAddResponseDto;
import com.springboot.harubi.Domain.Entity.Member;
import com.springboot.harubi.Domain.Entity.MemberGroup;
import com.springboot.harubi.Domain.Entity.Study;
import com.springboot.harubi.Domain.Entity.StudyGroup;
import com.springboot.harubi.Exception.BaseException;
import com.springboot.harubi.Repository.MemberRepository;
import com.springboot.harubi.Repository.StudyGroupRepository;
import com.springboot.harubi.Repository.StudyRepository;
import com.springboot.harubi.Domain.Dto.response.AddReferenceResponseDto;
import com.springboot.harubi.Domain.Entity.*;
import com.springboot.harubi.Repository.ReferenceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class GroupService {
    private final StudyGroupRepository studyGroupRepository;
    private final MemberRepository memberRepository;
    private final StudyRepository studyRepository;
    private final ReferenceRepository referenceRepository;

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

    public GroupDetailResponseDto getGroupDetails(Long group_id) {
        StudyGroup studyGroup = studyGroupRepository.findById(group_id)
                .orElseThrow(() -> new BaseException(404, "해당 그룹이 존재하지 않습니다."));

        List<GroupDetailResponseDto.StudyInfo> studyInfoList = studyGroup.getStudies().stream()
                .map(study -> new GroupDetailResponseDto.StudyInfo(
                        study.getStudy_end_date().toString(),
                        study.getStudy_text()
                ))
                .collect(Collectors.toList());

        List<GroupDetailResponseDto.MemberInfo> memberInfoList = studyGroup.getMemberGroups().stream()
                .map(memberGroup -> new GroupDetailResponseDto.MemberInfo(
                        memberGroup.getMember().getIcon(),
                        memberGroup.getMember().getName()
                ))
                .collect(Collectors.toList());

        List<GroupDetailResponseDto.ReferenceLink> referenceLinkList = studyGroup.getReferences().stream()
                .map(reference -> new GroupDetailResponseDto.ReferenceLink(
                        reference.getReference_name(),
                        reference.getReference_url()
                ))
                .toList();

        return new GroupDetailResponseDto(studyInfoList, memberInfoList, referenceLinkList);
    }

    @Transactional
    public StudyAddResponseDto makePlan(Long group_id, StudyAddRequestDto studyAddRequestDto) {
        StudyGroup studyGroup = studyGroupRepository.findById(group_id)
                .orElseThrow(() -> new BaseException(404, "존재하지 않는 그룹입니다."));

        Study study = new Study();
        study.setStudy_text(studyAddRequestDto.getStudy_name());
        study.setStudy_emoji(studyAddRequestDto.getStudy_emoji());
        study.setStudy_start_date(Date.valueOf(LocalDate.now()));
        study.setStudy_end_date(new Date(studyAddRequestDto.getStudy_end_date().getTime()));
        study.setStudyGroup(studyGroup);

        Study savedStudy = studyRepository.save(study);

        return new StudyAddResponseDto(savedStudy.getStudy_id());
    }

    public AddReferenceResponseDto addReference(Long groupId, AddReferenceRequestDto requestDto) {
        // 그룹 조회
        StudyGroup studyGroup = studyGroupRepository.findById(groupId)
                .orElseThrow(() -> new IllegalArgumentException("해당 그룹을 찾을 수 없습니다."));

        // Reference 엔티티 생성
        Reference reference = new Reference();
        reference.setReference_name(requestDto.getReference_name());
        reference.setReference_url(requestDto.getReference_url()); // 요청 값 설정
        reference.setStudyGroup(studyGroup);

        // 저장
        Reference savedReference = referenceRepository.save(reference);

        // 응답 DTO 생성
        return new AddReferenceResponseDto(savedReference.getReference_id());
    }
}
