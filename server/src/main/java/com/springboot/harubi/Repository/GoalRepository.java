package com.springboot.harubi.Repository;

import com.springboot.harubi.Domain.Entity.Goal;
import com.springboot.harubi.Domain.Entity.GoalDateStatus;
import com.springboot.harubi.Domain.Entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Repository
public interface GoalRepository extends JpaRepository<Goal, Long> {
    @Query("SELECT g FROM Goal g JOIN g.goalDateStatuses gs WHERE gs.goal_date = CURRENT_DATE AND g.member = :member")
    List<Goal> findTodayGoalsByMember(@Param("member") Member member);

    List<Goal> findByMember(Member member);
}
