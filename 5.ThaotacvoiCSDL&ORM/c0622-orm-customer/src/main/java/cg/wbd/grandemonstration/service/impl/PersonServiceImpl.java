package cg.wbd.grandemonstration.service.impl;

import cg.wbd.grandemonstration.model.Customer;
import cg.wbd.grandemonstration.model.Person;
import cg.wbd.grandemonstration.service.PersonService;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;


@Service
public class PersonServiceImpl implements PersonService {

    private static SessionFactory sessionFactory;
    private static EntityManager entityManager;


    static {
        try {
            sessionFactory = new Configuration()
                    .configure("hibernate.conf.xml")
                    .buildSessionFactory();
            entityManager = sessionFactory.createEntityManager();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }


    @Override
    public List<Person> findAll() {
        return null;
    }

    @Override
    public Person findOne(Long id) {
        return null;
    }

    @Override
    public Person save(Person person) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            Person newPerson = new Person();
            newPerson.setId(0L);
            newPerson.setFullName(person.getFullName());
            newPerson.setYob(person.getYob());
            newPerson.setVehicleNumber(person.getVehicleNumber());
            newPerson.setDepartureDay(person.getDepartureDay());
            session.save(newPerson);
            transaction.commit();

            return newPerson;

        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return null;
    }

    @Override
    public List<Person> save(List<Person> persons) {
        return null;
    }

    @Override
    public boolean exists(Long id) {
        return false;
    }

    @Override
    public List<Person> findAll(List<Long> ids) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void delete(Person person) {

    }

    @Override
    public void delete(List<Person> persons) {

    }

    @Override
    public void deleteAll() {

    }
}
