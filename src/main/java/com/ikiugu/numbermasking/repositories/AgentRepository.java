package com.ikiugu.numbermasking.repositories;

import com.ikiugu.numbermasking.models.Agent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AgentRepository extends JpaRepository<Agent, Long> {
    Optional<Agent> findAgentByPhoneNumber(String phoneNumber);
}
