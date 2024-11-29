package com.springboot.harubi.Repository;

import com.springboot.harubi.Domain.Entity.Member;
import com.springboot.harubi.Domain.Entity.Plan;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PlanRepository extends JpaRepository<Plan, Long> {

    @Query("SELECT p FROM Plan p WHERE p.member = :member ORDER BY p.goal_date ASC")
    List<Plan> findTop3PlansByMember(@Param("member") Member member, Pageable pageable);
}
