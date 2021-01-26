package com.ikiugu.numbermasking.repositories;

import com.ikiugu.numbermasking.models.Agent;
import com.ikiugu.numbermasking.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findCustomerByPhoneNumber(String phoneNumber);

    Optional<Customer> findCustomerByCalling(boolean calling);

    Optional<Customer> findCustomersByAgentAndCalling(Agent agent, boolean calling);
}
