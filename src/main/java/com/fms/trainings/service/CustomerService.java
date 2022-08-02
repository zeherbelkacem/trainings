package com.fms.trainings.service;

import com.fms.trainings.entities.Customer;
import org.springframework.http.HttpStatus;

public interface CustomerService {
    public Customer saveCustomer(Customer customer);
}
