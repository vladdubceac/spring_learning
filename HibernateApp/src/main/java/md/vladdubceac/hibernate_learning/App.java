package md.vladdubceac.hibernate_learning;

import md.vladdubceac.hibernate_learning.model.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        Configuration configuration = new Configuration().addAnnotatedClass(Person.class);

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        try {
            session.beginTransaction();

//            Person person1 = new Person("Test1",30);
//            Person person2 = new Person("Test2",40);
//            Person person3 = new Person("Test3",50);

//            session.save(person1);
//            session.save(person2);
//            session.save(person3);

//            Person person = session.get(Person.class,2);
//            person.setName("New name");
//            session.delete(person);

//            Person person = new Person("Some name",60);
//            session.persist(person);

//            List<Person> people = session.createQuery("FROM Person WHERE name like 'T%'").getResultList();
//            for (Person person : people) {
//                System.out.println(person);
//            }

//            int updated = session.createQuery("UPDATE Person SET name = 'Test' WHERE age < 30").executeUpdate();
//            System.out.println("Updated rows : " + updated);

            int deletedRows = session.createQuery("DELETE from Person WHERE age < 30").executeUpdate();
            System.out.println("Deleted rows : " + deletedRows);

            session.getTransaction().commit();

//            System.out.println(person.getId());
        } finally {
            sessionFactory.close();
        }

    }
}
