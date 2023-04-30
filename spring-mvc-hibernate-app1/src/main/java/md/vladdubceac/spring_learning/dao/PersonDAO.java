package md.vladdubceac.spring_learning.dao;

import md.vladdubceac.spring_learning.models.Person;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManagerFactory;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class PersonDAO {

    private final EntityManagerFactory entityManagerFactory;

    @Autowired
    public PersonDAO(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Transactional(readOnly = true)
    public List<Person> index() {
        Session session = entityManagerFactory.unwrap(Session.class);

        return session.createQuery("SELECT p FROM Person p", Person.class).getResultList();
    }

    @Transactional(readOnly = true)
    public Person show(int id) {
        Session session = entityManagerFactory.unwrap(Session.class);
        return session.get(Person.class, id);
    }

    @Transactional
    public void save(Person person) {
        Session session = entityManagerFactory.unwrap(Session.class);
        session.save(person);
    }

    @Transactional
    public void update(int id, Person updatedPerson) {
        Session session = entityManagerFactory.unwrap(Session.class);

        Person personToBeUpdated = session.get(Person.class, id);

        personToBeUpdated.setAge(updatedPerson.getAge());
        personToBeUpdated.setName(updatedPerson.getName());
        personToBeUpdated.setEmail(updatedPerson.getEmail());
    }

    @Transactional
    public void delete(int id) {
        Session session = entityManagerFactory.unwrap(Session.class);
        session.remove(session.get(Person.class, id));
    }

    @Transactional(readOnly = true)
    public void testNPlus1() {
        Session session = entityManagerFactory.unwrap(Session.class);

        // 1 Query
//        List<Person> personList = session.createQuery("SELECT P FROM Person P", Person.class).getResultList();

        // N queries
//        for(Person person : personList){
//            System.out.println("Person " + person.getName() + " has: " + person.getItems());
//        }

        // SOLUTION
        // SQL : LEFT JOIN
        Set<Person> people = new HashSet<>(session.createQuery("select p from Person p LEFT JOIN FETCH p.items").getResultList());

        people.forEach(person -> System.out.println("Person " + person.getName() + " has items : " + person.getItems()));
    }
}