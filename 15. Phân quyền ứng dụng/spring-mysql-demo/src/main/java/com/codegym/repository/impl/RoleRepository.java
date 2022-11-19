package com.codegym.repository.impl;



import com.codegym.model.Role;
import com.codegym.repository.IRoleRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class RoleRepository implements IRoleRepository {

    @PersistenceContext
    EntityManager em;

    @Override
    public List<Role> findAll() {
        TypedQuery<Role> query = em.createQuery("SELECT r FROM Role r", Role.class);
        return query.getResultList();
    }

    @Override
    public Role findById(Long id) {
        TypedQuery<Role> query = em.createQuery("SELECT r FROM Role r WHERE r.id =: id", Role.class);
        query.setParameter("id", id);
        try {
            return query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void save(Role model) {
        if (model.getId() != null) {
            em.merge(model);
        } else  {
            em.persist(model);
        }
    }

    @Override
    public void remove(Long id) {
        Role role = findById(id);
        if (role != null) {
            em.remove(role);
        }
    }
}
