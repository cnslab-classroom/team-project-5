package com.springboot.harubi.Repository;

import com.springboot.harubi.Domain.Entity.Member;
import com.springboot.harubi.Domain.Entity.Plan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface PlanRepository extends JpaRepository<Plan, Long> {

    @Query("SELECT p FROM Plan p WHERE p.member = :member AND DATE(p.plan_date) = DATE(:today) ORDER BY p.plan_date")
    Page<Plan> findByMemberAndPlanDate(@Param("member") Member member, @Param("today") Date today, Pageable pageable);

}
