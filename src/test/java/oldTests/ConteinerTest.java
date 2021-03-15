//package oldTests;
//
//
//import org.junit.*;
//
//import org.junit.runner.RunWith;
//import org.junit.runners.MethodSorters;
//import org.rimmar.dao.PersonDAO;
//import org.rimmar.models.Hobby;
//import org.rimmar.models.Person;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.annotation.DirtiesContext;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.transaction.annotation.Transactional;
//import org.testcontainers.containers.PostgreSQLContainer;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//import java.util.Optional;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes =)
//@FixMethodOrder(MethodSorters.NAME_ASCENDING)
////@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
////@Transactional
//public class ConteinerTest {
//
//
//    @ClassRule
//    public static PostgreSQLContainer postgreSQLContainer = PostgresqlConteiner.getInstance();
//
//    @Autowired
//    private PersonDAO personDAO;
//
//    @Test
//    @Transactional
//    public void aMethod_whenGetAll_thenReturnListWithPersons(){
//        Person person1 = new Person();
//        person1.setName("Modest");
//        person1.setHobbyList(new ArrayList<>(Arrays.asList(
//                new Hobby("Skiing"),
//                new Hobby("Kayaking"),
//                new Hobby("Gaming")
//        )));
//        personDAO.savePerson(person1);
//        Person person2 = new Person();
//        person2.setName("Petro");
//        person2.setHobbyList(new ArrayList<>(Arrays.asList(
//                new Hobby("Shokolad"),
//                new Hobby("Konditer"),
//                new Hobby("Politik")
//        )));
//        personDAO.savePerson(person2);
//        Person person3 = new Person();
//        person3.setName("Vasil");
//        person3.setHobbyList(new ArrayList<>(Arrays.asList(
//                new Hobby("Racing"),
//                new Hobby("Spamming"),
//                new Hobby("Gaming")
//        )));
//        personDAO.savePerson(person3);
//        List<Person> getAll = personDAO.getAllPersons();
//        Assert.assertEquals(getAll.size(), 3);
//    }
//
//    @Test
//    @Transactional
//    public void whenPersist_thenNoExceptionThrown() {
//        Person person1 = new Person();
//        person1.setName("Jorik");
//        person1.setHobbyList(new ArrayList<>(Arrays.asList(
//                new Hobby("Reading"),
//                new Hobby("Traveling"),
//                new Hobby("Blogging")
//        )));
//        personDAO.savePerson(person1);
//
//    }
//
//    @Test
//    @Transactional
//    public void whenEntityPersisted_ThenItsRetrievedFromDB() {
//        Long savedPersonId = personDAO.savePerson(
//                "Peter", Arrays.asList("Manting", "Zanting", "Wanting", "Qwerting", "Asdeing"));
//
//        Optional<Person> personFetched = personDAO.getById(savedPersonId);
//
//        Assert.assertTrue(personFetched.isPresent());
//    }
//
//    @Test
//    @Transactional
//    public void whenGetByName_ThenItsRetrievedFromDB() {
//        Person person1 = new Person();
//        person1.setName("Gosling");
//        person1.setHobbyList(new ArrayList<>(Arrays.asList(
//                new Hobby("Music"),
//                new Hobby("Guitar"),
//                new Hobby("Bonjo")
//        )));
//        personDAO.savePerson(person1);
//
//        Optional<Person> personFetched = personDAO.getByName(person1.getName());
//
//        Assert.assertTrue(personFetched.isPresent());
//    }
//
//    @Test
//    @Transactional
//    public void whenRecordNotExists_thenGetByIdReturnsEmptyOptional() {
//        Optional<Person> personFetched = personDAO.getById(61616L);
//        Assert.assertFalse(personFetched.isPresent());
//    }
//
//    @Test
//    @Transactional
//    public void whenRemove_thenTrueAndNoPersonById(){
//        Person person1 = new Person();
//        person1.setName("Korjik");
//        person1.setHobbyList(new ArrayList<>(Arrays.asList(
//                new Hobby("Pie"),
//                new Hobby("Lie"),
//                new Hobby("Die")
//        )));
//        personDAO.savePerson(person1);
//
//        Long idPeron = person1.getId();
//
//        Assert.assertTrue(personDAO.removePerson(person1));
//
//        Optional<Person> personFetched = personDAO.getById(idPeron);
//
//        Assert.assertFalse(personFetched.isPresent());
//
//    }
//
//    @Test
//    @Transactional
//    public void whenRemoveById_thenNoPersonByName(){
//        Person person1 = new Person();
//        person1.setName("Bobik");
//        person1.setHobbyList(new ArrayList<>(Arrays.asList(
//                new Hobby("Bones"),
//                new Hobby("Barking"),
//                new Hobby("Cats")
//        )));
//        personDAO.savePerson(person1);
//
//        Optional<Person> personFromDB = personDAO.getByName(person1.getName());
//        personDAO.removePersonById(personFromDB.get().getId());
//
//        Optional<Person> personFetched = personDAO.getByName(person1.getName());
//        Assert.assertFalse(personFetched.isPresent());
//
//    }
//
//    @Test
//    @Transactional
//    public void whenUpdated_thenNameIsChange(){
//        Person person1 = new Person();
//        person1.setName("Neo");
//        person1.setHobbyList(new ArrayList<>(Arrays.asList(
//                new Hobby("Trinity"),
//                new Hobby("Guns"),
//                new Hobby("Rabbit")
//        )));
//        personDAO.savePerson(person1);
//
//        Optional<Person> personFetched = personDAO.getByName(person1.getName());
//        Person personToUpdate = personFetched.get();
//        personToUpdate.setName("TheChosenOne");
//
//        personDAO.updatePerson(personToUpdate);
//
//        Optional<Person> personFetched1 = personDAO.getByName(person1.getName());
//        Assert.assertFalse(personFetched1.isPresent());
//
//        Optional<Person> personFetched2 = personDAO.getByName(personToUpdate.getName());
//        Assert.assertTrue(personFetched2.isPresent());
//
//    }
//
//
//
//
//}
