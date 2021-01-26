package com.ikiugu.numbermasking.dtos;

import com.ikiugu.numbermasking.models.Customer;
import lombok.Data;

@Data
public class CustomerDto {
    private Customer customer;
    private String agentPhoneNumber;
}
