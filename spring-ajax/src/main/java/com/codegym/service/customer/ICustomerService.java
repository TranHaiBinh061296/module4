package com.codegym.service.customer;

import com.codegym.model.Customer;
import com.codegym.model.Deposit;
import com.codegym.model.Transfer;
import com.codegym.service.IGeneralService;

import java.util.List;
import java.util.Optional;

public interface ICustomerService extends IGeneralService<Customer> {
    Optional<Customer> findByEmail(String email);

    Optional<Customer> findByEmailAndIdIsNot(String email, Long id);

    List<Customer> findAllByFullNameLikeOrEmailLike(String fullName, String email);

    List<Customer> findAllByDeletedIsFalse();

    List<Customer> findAllByDeletedIsFalseAndIdNot(Long id);

    void deposit(Deposit deposit, Customer customer);

    void transfer(Transfer transfer);
}
