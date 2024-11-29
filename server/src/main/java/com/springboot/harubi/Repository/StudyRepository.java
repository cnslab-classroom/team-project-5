package com.springboot.harubi.Repository;

import com.springboot.harubi.Domain.Entity.Member;
import com.springboot.harubi.Domain.Entity.Study;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudyRepository extends JpaRepository<Study, Long> {

    @Query("SELECT s FROM Study s JOIN s.studyGroup sg JOIN sg.memberGroups mg WHERE mg.member = :member ORDER BY s.study_start_date DESC")
    List<Study> findTop3ByMember(@Param("member") Member member);
}
