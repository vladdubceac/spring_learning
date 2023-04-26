package md.vladdubceac.hibernate_learning;

import md.vladdubceac.hibernate_learning.model.Item;
import md.vladdubceac.hibernate_learning.model.Passport;
import md.vladdubceac.hibernate_learning.model.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class OneToOneMapping {
    public static void main(String[] args) {
        Configuration configuration = new Configuration().addAnnotatedClass(Person.class)
                .addAnnotatedClass(Item.class).addAnnotatedClass(Passport.class);

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        try {
            session.beginTransaction();

            // addPersonAndPassport(session,new Person("Test person",50),12345);

            getPersonAndPassport(session, 11);

            getPassportAndPerson(session, 11);

            updatePersonPassport(session, 11, 77777);

            deletePerson(session, 11);

             session.getTransaction().commit();
        }finally {
            sessionFactory.close();
        }
    }

    private static void deletePerson(Session session, int personId) {
        Person person =session.get(Person.class, personId);
        session.remove(person);
    }

    private static void updatePersonPassport(Session session, int personId, int passportNumber) {
        Person person = session.get(Person.class, personId);
        person.getPassport().setPassportNumber(passportNumber);
    }

    private static void getPassportAndPerson(Session session, int passportPersonId) {
        Passport passport = session.get(Passport.class, passportPersonId);
        System.out.println(passport.getPerson());
    }

    private static void getPersonAndPassport(Session session, int personId) {
        Person person = session.get(Person.class, personId);
        System.out.println(person.getPassport().getPassportNumber());
    }

    private static void addPersonAndPassport(Session session, Person person, int passportNumber) {
        Passport passport = new Passport(passportNumber);
        person.setPassport(passport);
        session.save(person);
    }
}
