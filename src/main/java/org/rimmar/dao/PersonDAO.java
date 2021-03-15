package org.rimmar.dao;

import org.hibernate.Criteria;
import org.rimmar.models.Hobby;
import org.rimmar.models.Person;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class PersonDAO {


    @PersistenceContext
    private EntityManager entityManager;

    public Long savePerson(Person person) {
        entityManager.persist(person);
        return person.getId();
    }

    public Long savePerson(String name, List<String> hobbyList) {

        Person person = new Person();

        person.setName(name);
        person.setHobbyList(new ArrayList<>());
        for (int i = 0; i < hobbyList.size(); i++) {
            person.getHobbyList().add(new Hobby(hobbyList.get(i)));
        }

        entityManager.persist(person);
        return person.getId();
    }

    public List<Person> getAllPersons() {
        return entityManager.createQuery("SELECT p FROM Person p", Person.class).getResultList();
    }

    public Optional<Person> getById(Long id){
        Person person = entityManager.find(Person.class, id);
        return Optional.ofNullable(person);
    }

    public Optional<Person> getByName(String name){

        List<Person> persons = entityManager.createQuery(
                "SELECT person FROM Person person where person.name = :param", Person.class).setParameter("param", name).getResultList();
if (persons.size()!=0) {
    return Optional.ofNullable(persons.get(0));
}else return Optional.empty();
    }


    public boolean removePerson(Person person){
        entityManager.remove(entityManager.contains(person) ? person : entityManager.merge(person));
        return true;
    }

    public int removePersonById(Long id){
       return entityManager.createQuery("delete from Person where id = :id" ).setParameter("id", id).executeUpdate();
    }

    public Person updatePerson(Person person){
          return entityManager.merge(person);
    }

}

