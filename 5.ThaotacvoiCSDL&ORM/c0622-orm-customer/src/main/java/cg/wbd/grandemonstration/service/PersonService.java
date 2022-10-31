package cg.wbd.grandemonstration.service;

import cg.wbd.grandemonstration.model.Customer;
import cg.wbd.grandemonstration.model.Person;

import java.util.List;

public interface PersonService {

    List<Person> findAll();

    Person findOne(Long id);

    Person save(Person person);

    List<Person> save(List<Person> persons);

    boolean exists(Long id);

    List<Person> findAll(List<Long> ids);

    long count();

    void delete(Long id);

    void delete(Person person);

    void delete(List<Person> persons);

    void deleteAll();
}
