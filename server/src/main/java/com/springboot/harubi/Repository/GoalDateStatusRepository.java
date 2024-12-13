package com.springboot.harubi.Repository;

import com.springboot.harubi.Domain.Entity.GoalDateStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface GoalDateStatusRepository extends JpaRepository<GoalDateStatus, Long> {

    @Query("SELECT gds FROM GoalDateStatus gds WHERE gds.goal.member.member_id = :memberId AND gds.goal_date = :goalDate")
    List<GoalDateStatus> findByMemberIdAndGoalDate(@Param("memberId") Long memberId, @Param("goalDate") LocalDate goalDate);
}

