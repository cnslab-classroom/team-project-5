package com.springboot.harubi.Repository;

import com.springboot.harubi.Domain.Entity.Streak;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StreakRepository extends JpaRepository<Streak, Long> {

    @Query("SELECT s FROM Streak s WHERE s.member.member_id = :memberId")
    Optional<Streak> findByMemberId(@Param("memberId") Long memberId);
}