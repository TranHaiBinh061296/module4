package com.codegym.service.customer;

import com.codegym.model.Customer;
import com.codegym.model.Deposit;
import com.codegym.model.WithDraw;
import com.codegym.service.IGeneralService;

import java.util.List;

public interface ICustomerService extends IGeneralService<Customer> {

    List<Customer> findAllByFullNameLikeOrEmailLike(String fullName, String email);

    void deposit(Deposit deposit, Customer customer);
    boolean withdraw(WithDraw withDraw, Customer customer);
}
