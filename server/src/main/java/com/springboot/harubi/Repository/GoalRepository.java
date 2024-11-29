package com.springboot.harubi.Repository;

import com.springboot.harubi.Domain.Entity.Goal;
import com.springboot.harubi.Domain.Entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoalRepository extends JpaRepository<Goal, Long> {
    @Query("SELECT g FROM Goal g WHERE g.member = :member AND DATE(g.goal_start_date) <= CURRENT_DATE AND DATE(g.goal_end_date) >= CURRENT_DATE")
    List<Goal> findTodayGoalsByMember(@Param("member") Member member);
}
