package com.springinaction.pizza.service;

import com.springinaction.pizza.domain.Customer;
import org.springframework.stereotype.Service;

@Service
public interface CustomerService {

    Customer lookup(String phoneNumber) throws CustomerNotFoundException;

}
