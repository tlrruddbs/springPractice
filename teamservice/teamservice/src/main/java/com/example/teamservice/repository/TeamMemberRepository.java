package com.example.teamservice.repository;

import com.example.teamservice.domain.entity.TeamMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamMemberRepository extends JpaRepository<TeamMember, Long> {
    public TeamMember findByUserId(Long userId);
}
