package com.codegym.service.customer;

import com.codegym.model.Customer;
import com.codegym.model.Deposit;
import com.codegym.model.Withdraw;
import com.codegym.service.IGeneralService;

import java.util.List;

public interface ICustomerService extends IGeneralService<Customer> {


    void deposit(Deposit deposit, Customer customer);
    boolean withdraw(Withdraw withDraw, Customer customer);
    Iterable<Customer> findAllByIdNot(Long id);
}