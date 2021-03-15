import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.rimmar.dao.PersonRepository;
import org.rimmar.models.Hobby;
import org.rimmar.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.containers.PostgreSQLContainer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfiguration.class)
@Transactional
public class ConteinerWithRepositoryTest {

    @ClassRule
    public static PostgreSQLContainer postgreSQLContainer = PostgresqlConteiner.getInstance();
    @Autowired
    PersonRepository personRepository;

    @Test
    public void whenGetAll_thenReturnListWithPersons(){
        Person person1 = new Person();
        person1.setName("Modest");
        person1.setHobbyList(new ArrayList<>(Arrays.asList(
                new Hobby("Skiing"),
                new Hobby("Kayaking"),
                new Hobby("Gaming")
        )));
        personRepository.save(person1);
        Person person2 = new Person();
        person2.setName("Petro");
        person2.setHobbyList(new ArrayList<>(Arrays.asList(
                new Hobby("Shokolad"),
                new Hobby("Konditer"),
                new Hobby("Politik")
        )));
        personRepository.save(person2);
        Person person3 = new Person();
        person3.setName("Vasil");
        person3.setHobbyList(new ArrayList<>(Arrays.asList(
                new Hobby("Racing"),
                new Hobby("Spamming"),
                new Hobby("Gaming")
        )));
        personRepository.save(person3);

        Iterable<Person> getAll = personRepository.findAll();
        List<Person> persons = new ArrayList<>();
        getAll.forEach(person -> persons.add(person));

        Assert.assertTrue(persons.size() > 2);
    }

    @Test
    public void whenPersist_thenNoExceptionThrown() {
        Person person1 = new Person();
        person1.setName("Jorik");
        person1.setHobbyList(new ArrayList<>(Arrays.asList(
                new Hobby("Reading"),
                new Hobby("Traveling"),
                new Hobby("Blogging")
        )));
        personRepository.save(person1);

    }

    @Test
    public void whenEntityPersisted_ThenItsRetrievedFromDB() {
        Person person = new Person (
                "Peter", Arrays.asList(
                new Hobby("Manting"), new Hobby("Zanting"), new Hobby("Wanting"),
                new Hobby("Qwerting"), new Hobby("Asdeing")));

        Long savedPersonId = personRepository.save( person ).getId();

        Optional<Person> personFetched = personRepository.findById(savedPersonId);

        Assert.assertTrue(personFetched.isPresent());
    }

    @Test
    public void whenfindByName_ThenItsRetrievedFromDB() {
        Person person1 = new Person();
        person1.setName("Gosling");
        person1.setHobbyList(new ArrayList<>(Arrays.asList(
                new Hobby("Music"),
                new Hobby("Guitar"),
                new Hobby("Bonjo")
        )));
        personRepository.save(person1);


        Optional<Person> personFetched = personRepository.findByName((person1.getName())).stream().findAny();

        Assert.assertTrue(personFetched.isPresent());
    }

    @Test
    public void whenRecordNotExists_thenfindByIdReturnsEmptyOptional() {
        Optional<Person> personFetched = personRepository.findById(61616L);
        Assert.assertFalse(personFetched.isPresent());
    }

    @Test
    public void whenRemove_thenTrueAndNoPerson(){
        Person person1 = new Person();
        person1.setName("Korjik");
        person1.setHobbyList(new ArrayList<>(Arrays.asList(
                new Hobby("Pie"),
                new Hobby("Lie"),
                new Hobby("Die")
        )));
        personRepository.save(person1);

        personRepository.delete(person1);

        Optional<Person> personFetched = personRepository.findById(person1.getId());

        Assert.assertFalse(personFetched.isPresent());

    }

    @Test
    public void whenRemoveById_thenNoPersonByName(){
        Person person1 = new Person();
        person1.setName("Bobik");
        person1.setHobbyList(new ArrayList<>(Arrays.asList(
                new Hobby("Bones"),
                new Hobby("Barking"),
                new Hobby("Cats")
        )));
        personRepository.save(person1);

        Optional<Person> personFromDB = personRepository.findByName(person1.getName()).stream().findAny();
        personRepository.deleteById(personFromDB.get().getId());

        Optional<Person> personFetched = personRepository.findById(personFromDB.get().getId());
        Assert.assertFalse(personFetched.isPresent());

    }

    @Test
    public void whenUpdated_thenNameIsChange(){
        String originalName = "Neo";
        Person person1 = new Person();
        person1.setName(originalName);
        person1.setHobbyList(new ArrayList<>(Arrays.asList(
                new Hobby("Trinity"),
                new Hobby("Guns"),
                new Hobby("Rabbit")
        )));
        personRepository.save(person1);

        Optional<Person> personFetched = personRepository.findByName(person1.getName()).stream().findAny();
        Person personToUpdate = personFetched.get();
        personToUpdate.setName("TheChosenOne");

        personRepository.save(personToUpdate);

        Optional<Person> personFetched1 = personRepository.findByName(originalName).stream().findAny();
        Assert.assertFalse(personFetched1.isPresent());

        Optional<Person> personFetched2 = personRepository.findByName(personToUpdate.getName()).stream().findAny();
        Assert.assertTrue(personFetched2.isPresent());


    }
}
