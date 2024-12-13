package com.springboot.harubi.Repository;

import com.springboot.harubi.Domain.Entity.GoalDateStatus;
import com.springboot.harubi.Domain.Entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface GoalDateStatusRepository extends JpaRepository<GoalDateStatus, Long> {
    @Query("SELECT gds FROM GoalDateStatus gds " +
            "WHERE gds.goal.member = :member AND gds.goal_date >= :startDate AND gds.goal_date <= :endDate " +
            "ORDER BY gds.goal_date ASC")
    List<GoalDateStatus> findByMemberAndDateRange(@Param("member") Member member,
                                                       @Param("startDate") Date startDate,
                                                       @Param("endDate") Date endDate);
}
