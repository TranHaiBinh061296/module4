package com.codegym.service.customer;

import com.codegym.model.Customer;
import com.codegym.model.Deposit;
import com.codegym.model.Transfer;
import com.codegym.model.Withdraw;
import com.codegym.service.IGeneralService;

import java.util.List;

public interface ICustomerService extends IGeneralService<Customer> {


    void deposit(Deposit deposit, Customer customer);
    void withdraw(Withdraw withdraw, Customer customer);
    List<Customer> findAllByIdNot(Long senderId);

    void transfer(Transfer transfer);
    List<Customer> findAllByDeletedIsFalse();
}