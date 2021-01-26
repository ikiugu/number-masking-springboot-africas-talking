package com.ikiugu.numbermasking.controllers;

import com.africastalking.ActionBuilder;
import com.africastalking.voice.action.Dial;
import com.africastalking.voice.action.Say;
import com.ikiugu.numbermasking.models.Agent;
import com.ikiugu.numbermasking.models.Customer;
import com.ikiugu.numbermasking.models.VoiceResponse;
import com.ikiugu.numbermasking.repositories.AgentRepository;
import com.ikiugu.numbermasking.repositories.CustomerRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class VoiceController {

    private final AgentRepository agentRepository;
    private final CustomerRepository customerRepository;

    public VoiceController(AgentRepository agentRepository, CustomerRepository customerRepository) {
        this.agentRepository = agentRepository;
        this.customerRepository = customerRepository;
    }

    @PostMapping("/callback")
    public String otherCallback(VoiceResponse response) {

        ActionBuilder actionBuilder = new ActionBuilder();

        Say say = null;

        // only do anything if the call is active. otherwise, we don't care
        if (response.getIsActive().equals("1") && response.getCallerNumber() != null) {
            Optional<Agent> agent = agentRepository.findAgentByPhoneNumber(response.getCallerNumber());

            if (agent.isPresent()) {
                // 1. Handle agent
                // no leads present
                if (agent.get().getCustomers().size() == 0) {
                    say = new Say("Sorry " + agent.get().getName() + ", you have no customers yet. Goodbye", false, Say.Voice.MAN);
                    return actionBuilder.say(say).build();
                }

                // leads present
                Optional<Customer> customer = customerRepository.findCustomersByAgentAndCalling(agent.get(), true);
                if (customer.isPresent()) {
                    say = new Say("Hi " + agent.get().getName() + ", we are now calling " + customer.get().getName());
                    List<String> numbers = new ArrayList<>();
                    numbers.add(customer.get().getPhoneNumber());
                    Dial dial = new Dial(numbers);

                    return actionBuilder.say(say).dial(dial).build();
                } else {
                    say = new Say("Sorry " + agent.get().getName() + ", you have no customers to call yet. Goodbye", false, Say.Voice.MAN);
                    return actionBuilder.say(say).build();
                }

            } else {
                // 2. Handle customer
                Customer customer = new Customer();
                customer.setName("John/Jane Doe");
                customer.setPhoneNumber(response.getCallerNumber());
                customer.setComment("Customer needs a callback");

                customerRepository.save(customer);

                say = new Say("Thank you for calling. We shall call you back as soon as possible. Goodbye", false, Say.Voice.WOMAN);
                return actionBuilder.say(say).build();
            }


        }


        return "ok";
    }


    @PostMapping("/events")
    public String eventsCallBack(VoiceResponse voiceResponse) {
        if (voiceResponse.getIsActive().equals("0") && voiceResponse.getCallSessionState().equals("Completed")) {
            Optional<Agent> agent = agentRepository.findAgentByPhoneNumber(voiceResponse.getCallerNumber());
            if (agent.isPresent()) {

                Optional<Customer> customer = customerRepository.findCustomersByAgentAndCalling(agent.get(), true);
                if (customer.isPresent()) {
                    Customer cust = customer.get();
                    cust.setCalling(false);
                    cust.setContacted(true);

                    customerRepository.save(cust);
                }
            }
        }


        return "ok";
    }

}
