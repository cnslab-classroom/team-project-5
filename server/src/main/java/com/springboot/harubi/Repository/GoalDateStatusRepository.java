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

    @Query("SELECT gds FROM GoalDateStatus gds " +
            "JOIN gds.goal goal " +
            "JOIN goal.member member " +
            "WHERE member.member_id = :memberId AND gds.goal_date = :goalDate")
    List<GoalDateStatus> findStatusesByMemberAndDate(@Param("memberId") Long memberId,
                                                     @Param("goalDate") Date goalDate);

    @Query("SELECT gds FROM GoalDateStatus gds " +
            "JOIN gds.goal goal " +
            "JOIN goal.member member " +
            "WHERE member.member_id = :memberId " +
            "AND gds.goal_status = true " +
            "ORDER BY gds.goal_date DESC")
    List<GoalDateStatus> findCompletedGoalsByMember(@Param("memberId") Long memberId);

    // 특정 날짜에 해당하는 goal_date_status 목록 가져오기
    @Query("SELECT g FROM GoalDateStatus g " +
            "WHERE g.goal_date = :goalDate AND g.goal.goal_id IN " +
            "(SELECT goal.goal_id FROM Goal goal WHERE goal.member.member_id = :memberId)")
    List<GoalDateStatus> findGoalsByDateAndMember(@Param("goalDate") LocalDate goalDate,
                                                  @Param("memberId") Long memberId);
}
