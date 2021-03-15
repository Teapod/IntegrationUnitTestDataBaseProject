package org.rimmar.dao;

import org.rimmar.models.Person;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PersonRepository extends CrudRepository<Person, Long> {
    List<Person> findByName( String name );
    void deleteAllByName ( String name );
    void deleteByName ( String name );
}
