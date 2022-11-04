package com.codegym.repository;

import com.codegym.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;


@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Iterable<Customer> findAllByIdIsNot(Long id);

    @Modifying
    @Query("UPDATE Customer AS c " +
            "SET c.balance = c.balance + :balance " +
            "WHERE c.id = :customerId")
    void incrementBalance(@Param("customerId") Long customerId, @Param("balance") BigDecimal balance);

    @Modifying
    @Query("UPDATE Customer AS c " +
            "SET c.balance = :balance " +
            "WHERE c.id = :customerId")
    void withdrawBalance(@Param("customerId") Long customerId, @Param("balance") BigDecimal balance);
}