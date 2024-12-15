package com.springboot.harubi.Repository;

import com.springboot.harubi.Domain.Entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String email);
    Optional<Member> findBySignId(String sign_id);
    Optional<Member> findById(Long memberId);
    boolean existsByEmail(String email);

    @Query("SELECT m.member_id FROM Member m")
    List<Long> findAllMemberIds();
}
