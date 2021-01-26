package com.ikiugu.numbermasking.controllers;

import com.ikiugu.numbermasking.models.Agent;
import com.ikiugu.numbermasking.models.Customer;
import com.ikiugu.numbermasking.repositories.AgentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/agent")
public class AgentController {
    private final AgentRepository agentRepository;
    Logger logger = LoggerFactory.getLogger(AgentController.class);

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
            logger.error("saving the agent failed");
        }

        logger.info("saving the agent was successful");
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
        logger.info("does agent exist? " + agentOptional.isPresent());
        return agentOptional.isPresent();
    }

    /**
     * This method returns leads (customers) for a particular agent using their phone number
     *
     * @param phoneNumber -> agent phone number
     * @return -> list of customers
     */
    @GetMapping("/leads/{phoneNumber}")
    public List<Customer> getAgentLeads(@PathVariable String phoneNumber) {
        List<Customer> customers = new ArrayList<>();
        Optional<Agent> agent = agentRepository.findAgentByPhoneNumber(phoneNumber);

        if (agent.isPresent()) {
            customers = agent.get().getCustomers();
        }

        logger.info("Agent leads are " + customers.size());

        return customers;
    }
}
