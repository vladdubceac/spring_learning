package md.vladdubceac.hibernate_learning;

import md.vladdubceac.hibernate_learning.model.Item;
import md.vladdubceac.hibernate_learning.model.Passport;
import md.vladdubceac.hibernate_learning.model.Person;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class FetchTypeApp {
    public static void main(String[] args) {
        Configuration configuration = new Configuration().addAnnotatedClass(Person.class)
                .addAnnotatedClass(Item.class).addAnnotatedClass(Passport.class);
        SessionFactory sessionFactory = configuration.buildSessionFactory();

        try {
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();

//            getPersonAndItems(session, 1);
//            getItemAndOwner(session, 1);
            Person person = getPersonAndItems(session,1);

            session.getTransaction().commit();
            // session.close()
            System.out.println("Session finished (session.close())");

            session = sessionFactory.getCurrentSession();
            session.beginTransaction();

            System.out.println("Inside second transaction");

            person = session.merge(person);

            // Hibernate.initialize(person.getItems());

            List<Item> items =session.createQuery("SELECT i FROM Item i WHERE i.owner.id = :personId", Item.class).setParameter("personId",person.getId()).getResultList();
            System.out.println(items);

            session.getTransaction().commit();

            System.out.println("Out of second session");
//            System.out.println(person.getItems());
        } finally {
            sessionFactory.close();
        }
    }

    private static Item getItemAndOwner(Session session, int itemId) {
        Item item = session.get(Item.class, itemId);
        System.out.println("Get item");

        System.out.println(item.getOwner());
        return item;
    }

    private static Person getPersonAndItems(Session session, int personId) {
        Person person = session.get(Person.class, personId);
        System.out.println("Get person");
//        System.out.println(person);

//        System.out.println(person.getItems());
//        Hibernate.initialize(person.getItems());

        return person;
    }
}
