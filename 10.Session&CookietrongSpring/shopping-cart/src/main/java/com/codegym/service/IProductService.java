package com.codegym.service;

import com.codegym.model.Product;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface IProductService {
    Iterable<Product> findAll();
    Optional<Product> findById(Long id);
}
