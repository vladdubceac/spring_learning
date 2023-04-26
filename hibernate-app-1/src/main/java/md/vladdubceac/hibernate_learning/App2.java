package md.vladdubceac.hibernate_learning;

import md.vladdubceac.hibernate_learning.model.Item;
import md.vladdubceac.hibernate_learning.model.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Stream;

public class App2 {
    public static void main(String[] args) {
        Configuration configuration = new Configuration().addAnnotatedClass(Person.class).addAnnotatedClass(Item.class);

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        try {
            session.beginTransaction();

            // savePersonAndItem(session,new Person("Test cascading", 30), new Item("Test cascading item"));

            savePersonAndItems(session, new Person("Test cascading 1", 30), new String[]{"Item 1", "Item 2", "Item 3"});

            session.getTransaction().commit();
        } finally {
            sessionFactory.close();
        }
    }

    private static void savePersonAndItems(Session session, Person person, String[] itemNames) {
        Stream.of(itemNames).forEach(s -> person.addItem(new Item(s)));

        session.save(person);
    }

    private static void savePersonAndItem(Session session, Person person, Item item) {
        person.setItems(new ArrayList<>(Collections.singletonList(item)));

//            session.persist(person);
        session.save(person);
    }
}
