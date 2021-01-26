package com.ikiugu.numbermasking.controllers;

import com.ikiugu.numbermasking.models.Agent;
import com.ikiugu.numbermasking.repositories.AgentRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/agent")
public class AgentController {
    private final AgentRepository agentRepository;

    public AgentController(AgentRepository agentRepository) {
        this.agentRepository = agentRepository;
    }

    /**
     * This method creates and saves an agent to the db
     *
     * @param agent -> data sent from the mobile app
     * @return -> boolean whether this was successful or not
     */
    @PostMapping("")
    public boolean createAgent(@RequestBody Agent agent) {

        if (doesAgentExist(agent)) {
            return false;
        }

        Agent newAgent = new Agent();
        newAgent.setName(agent.getName());
        newAgent.setPhoneNumber(agent.getPhoneNumber());

        boolean saved = true;

        try {
            agentRepository.save(newAgent);
        } catch (Exception ex) {
            saved = false;
        }

        return saved;
    }

    /**
     * This method checks if the agent being persisted exists in the database or not
     *
     * @param agent -> agent from the mobile app
     * @return -> boolean whether agent exists or not
     */
    private boolean doesAgentExist(Agent agent) {
        Optional<Agent> agentOptional = agentRepository.findAgentByPhoneNumber(agent.getPhoneNumber());
        return agentOptional.isPresent();
    }
}
