package com.example.teamservice.repository;

import com.example.teamservice.domain.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
    public Team findByName(String name);
}
