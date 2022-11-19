package com.codegym.repository.impl;






import com.codegym.model.Message;
import com.codegym.repository.IMessageRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class MessageRepository implements IMessageRepository {

    @PersistenceContext
    EntityManager em;

    @Override
    public List<Message> findAll() {
        TypedQuery<Message> query = em.createQuery("SELECT m FROM Message m", Message.class);
        return query.getResultList();
    }

    @Override
    public Message findById(Long id) {
        TypedQuery<Message> query = em.createQuery("SELECT m FROM Message  m WHERE m.id =: id", Message.class);
        try {
            return query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void save(Message model) {
        if (model.getId() != null) {
            em.merge(model);
        } else  {
            em.persist(model);
        }
    }

    @Override
    public void remove(Long id) {
        Message message = findById(id);
        if (message != null) {
            em.remove(message);
        }
    }
}
