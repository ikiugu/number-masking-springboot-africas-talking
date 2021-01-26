package com.ikiugu.numbermasking.controllers;

import com.ikiugu.numbermasking.dtos.CustomerDto;
import com.ikiugu.numbermasking.models.Customer;
import com.ikiugu.numbermasking.repositories.AgentRepository;
import com.ikiugu.numbermasking.repositories.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("customer")
public class CustomerController {
    private final CustomerRepository customerRepository;
    private final AgentRepository agentRepository;

    Logger logger = LoggerFactory.getLogger(CustomerController.class);

    public CustomerController(CustomerRepository customerRepository, AgentRepository agentRepository) {
        this.customerRepository = customerRepository;
        this.agentRepository = agentRepository;
    }

    /**
     * This method creates a customer with the agent phone number as part of the request
     *
     * @param customerDto -> custom data transfer object to enable saving of a customer
     * @return -> boolean whether customer registration was successful
     */
    @PostMapping("")
    public boolean createCustomer(@RequestBody CustomerDto customerDto) {

        if (doesCustomerExist(customerDto.getCustomer())) {
            return false;
        }


        Customer customer = new Customer();
        customer = customerDto.getCustomer();
        customer.setAgent(agentRepository.findAgentByPhoneNumber(customerDto.getAgentPhoneNumber()).get());

        boolean saved = true;
        try {
            customerRepository.save(customer);
        } catch (Exception ex) {
            logger.error("error creating the customer");
            saved = false;
        }

        logger.info("customer created successfully");
        return saved;
    }

    private boolean doesCustomerExist(Customer customer) {
        Optional<Customer> cust = customerRepository.findCustomerByPhoneNumber(customer.getPhoneNumber());
        logger.info("customer exists? " + cust.isPresent());
        return cust.isPresent();
    }

    /**
     * This method updates the customer lead with additional information. Should be called after making the api call
     *
     * @param customerDto         -> custom data transfer object to enable saving of a customer
     * @param customerPhoneNumber -> customer phone number to enable querying
     * @return -> boolean whether the update was successful
     */
    @PatchMapping("/{customerPhoneNumber}")
    public boolean updateCustomer(@RequestBody CustomerDto customerDto, @PathVariable String customerPhoneNumber) {

        Optional<Customer> optionalCustomer = customerRepository.findCustomerByPhoneNumber(customerPhoneNumber);

        if (optionalCustomer.isEmpty()) {
            logger.info("customer does not exist");
            return false;
        }

        boolean updated = true;


        Customer customer = optionalCustomer.get();
        customer.setContacted(customerDto.getCustomer().isContacted());
        customer.setComment(customerDto.getCustomer().getComment());
        customer.setCalling(customerDto.getCustomer().isCalling());

        try {
            customerRepository.save(customer);
        } catch (Exception ex) {
            updated = false;
            logger.error("customer updating failed");
        }

        logger.info("customer updating was successful");
        return updated;
    }
}
