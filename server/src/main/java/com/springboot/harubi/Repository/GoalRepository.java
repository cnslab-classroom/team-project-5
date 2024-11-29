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
    // 특정 Member의 Goal 중 상위 3개를 생성일 순으로 조회
    @Query("SELECT g FROM Goal g WHERE g.member = :member ORDER BY g.goal_date DESC")
    List<Goal> findTop3ByMember(@Param("member") Member member);

}
