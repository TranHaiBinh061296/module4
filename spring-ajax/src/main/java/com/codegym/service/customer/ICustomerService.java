package com.codegym.service.customer;

import com.codegym.model.Customer;
import com.codegym.model.Deposit;
import com.codegym.model.Transfer;
import com.codegym.model.Withdraw;
import com.codegym.service.IGeneralService;

import java.util.List;
import java.util.Optional;

public interface ICustomerService extends IGeneralService<Customer> {
    Customer deposit(Deposit deposit, Customer customer);
    Customer withdraw(Withdraw withdraw, Customer customer);
    List<Customer> findAllByIdNot(Long senderId);

    Optional<Customer> findByEmail(String email);
    Optional<Customer> findByEmailAndIdIsNot(String email, Long id);
    void transfer(Transfer transfer);
    List<Customer> findAllByDeletedIsFalse();
}
