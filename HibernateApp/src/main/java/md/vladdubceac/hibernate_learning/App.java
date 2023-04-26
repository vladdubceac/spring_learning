package md.vladdubceac.hibernate_learning;

import md.vladdubceac.hibernate_learning.model.Item;
import md.vladdubceac.hibernate_learning.model.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        Configuration configuration = new Configuration().addAnnotatedClass(Person.class).addAnnotatedClass(Item.class);

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        try {
            session.beginTransaction();

            // get items belonging to a person
            // getPersonItems(session, 3);

            // get owner from item
            // getOwnerFromItem(session, 5);

            // Add new items
            // addNewItems(session);

            // addNewPersonAndNewItem(session,new Person("Test person 2", 30), "Item from Hibernate 2");

            // deletePersonItems(session, 3);

            // deletePerson(session, 2);

            updateItemOwner(session, 1, 4);

            session.getTransaction().commit();
        } finally {
            sessionFactory.close();
        }

    }

    private static void updateItemOwner(Session session, int itemId, int personId) {
        Item item = session.get(Item.class, itemId);
        item.getOwner().getItems().remove(item);
        
        Person person = session.get(Person.class, personId);

        item.setOwner(person);
        person.getItems().add(item);
    }

    private static void deletePerson(Session session, int personId) {
        Person person = session.get(Person.class, personId);

        // SQL
        session.remove(person);

        person.getItems().forEach(item -> item.setOwner(null));
    }

    private static void deletePersonItems(Session session, int personId) {
        Person person = session.get(Person.class, personId);
        List<Item> items = person.getItems();

        // SQL
        for(Item item: items){
            session.remove(item);
        }

        // Doesn't generate SQL, but it's necessary to update the cache
        person.getItems().clear();
    }

    private static void addNewPersonAndNewItem(Session session, Person person, String itemName) {
        Item newItem = new Item(itemName, person);
        person.setItems(new ArrayList<>(Collections.singletonList(newItem)));

        session.save(person);

        session.save(newItem);
    }

    private static void addNewItems(Session session) {
        Person newItemOwner = session.get(Person.class, 2);

        Item newItem = new Item("Item from Hibernate", newItemOwner);

        newItemOwner.getItems().add(newItem);

        session.save(newItem);
    }

    private static void getOwnerFromItem(Session session, int itemId) {
        Item item = session.get(Item.class, itemId);
        System.out.println(item);

        Person owner = item.getOwner();
        System.out.println(owner);
    }

    private static void getPersonItems(Session session, int personId) {
        Person person = session.get(Person.class, personId);
        System.out.println(person);

        List<Item> items = person.getItems();

        System.out.println(items);
    }
}
