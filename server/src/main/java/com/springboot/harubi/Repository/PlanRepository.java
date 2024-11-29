package com.springboot.harubi.Repository;

import com.springboot.harubi.Domain.Entity.Plan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanRepository extends JpaRepository<Plan, Long> {
}
