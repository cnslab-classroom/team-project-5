package com.springboot.harubi.Repository;

import com.springboot.harubi.Domain.Entity.Goal;
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

    @Query("SELECT g FROM Goal g WHERE g.member = :member AND :today BETWEEN g.goal_start_date AND g.goal_end_date")
    Page<Goal> findByMemberAndDateRange(@Param("member") Member member, @Param("today") Date today, Pageable pageable);
    @Query("SELECT g FROM Goal g WHERE g.member = :member AND DATE(g.goal_start_date) <= CURRENT_DATE AND DATE(g.goal_end_date) >= CURRENT_DATE")
    List<Goal> findTodayGoalsByMember(@Param("member") Member member);
}
