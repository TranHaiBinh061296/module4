package com.codegym.service;

import com.codegym.model.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductService implements IProductService {

    private static final Map<Integer, Product> products;

    static {
        products = new HashMap<>();
        products.put(1, new Product(1, "Iphone14", 5000000,"Sieu mong","USA"));
        products.put(2, new Product(2,"Vsmart Phone",300000,"Chat luong","VN"));
        products.put(3, new Product(3, "Sam sung", 8900000,"Hien dai","Korea"));



    }

    @Override
    public List<Product> findAll() {
        return new ArrayList<>(products.values());
    }

    @Override
    public void save(Product product) {
        products.put(product.getId(), product);
    }

    @Override
    public Product findById(int id) {
        return products.get(id);
    }

    @Override
    public void update(int id, Product product) {
        products.put(id, product);
    }

    @Override
    public void remove(int id) {
        products.remove(id);
    }
    @Override
    public Product findByName(String name) {
        for (Product product:products.values()) {
            if (name.toUpperCase().equals(product.getName().toUpperCase())) {
                return product;
            }
        }
        return null;
    }
}
