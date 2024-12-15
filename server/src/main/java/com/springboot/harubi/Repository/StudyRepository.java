package com.springboot.harubi.Repository;

import com.springboot.harubi.Domain.Entity.Study;
import com.springboot.harubi.Domain.Entity.StudyGroup;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface StudyRepository extends JpaRepository<Study, Long> {

    @Query("SELECT s FROM Study s " +
            "JOIN s.studyGroup sg " +
            "WHERE sg.group_id = :studyGroupId " +
            "AND :today BETWEEN s.study_start_date AND s.study_end_date " +
            "ORDER BY s.study_start_date ASC")
    Page<Study> findByStudyGroupAndDateRange(
            @Param("studyGroupId") Long studyGroupId,
            @Param("today") Date today,
            Pageable pageable
    );

    @Query("SELECT s FROM Study s " +
            "WHERE s.studyGroup IN :studyGroups " +
            "AND :today BETWEEN s.study_start_date AND s.study_end_date")
    List<Study> findStudiesByStudyGroupsAndDate(@Param("studyGroups") List<StudyGroup> studyGroups,
                                                @Param("today") Date today,
                                                Pageable pageable);
}
