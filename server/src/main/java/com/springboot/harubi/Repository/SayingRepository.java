package com.springboot.harubi.Repository;

import com.springboot.harubi.Domain.Entity.Saying;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface SayingRepository extends JpaRepository<Saying, Long> {
    @Query(value = "SELECT * FROM saying ORDER BY RAND() LIMIT 1", nativeQuery = true)
    Optional<Saying> findRandomSaying();

}

