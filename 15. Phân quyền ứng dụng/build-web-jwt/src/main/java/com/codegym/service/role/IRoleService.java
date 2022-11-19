package com.codegym.service.role;

import com.codegym.model.Role;
import com.codegym.service.IGeneralService;

public interface IRoleService extends IGeneralService<Role> {
    Role findByName(String name);
}