package com.codegym.service;


import com.codegym.model.Role;

import java.util.List;

public interface IRoleService {
    List<Role> findAll();

    Role findById(Long id);

    void save(Role role);

    void remove(Long id);
}
