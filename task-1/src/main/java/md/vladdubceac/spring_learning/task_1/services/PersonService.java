package md.vladdubceac.spring_learning.task_1.services;

import md.vladdubceac.spring_learning.task_1.dao.PersonDAO;
import md.vladdubceac.spring_learning.task_1.models.Book;
import md.vladdubceac.spring_learning.task_1.models.Person;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PersonService {

    private final PersonDAO personDAO;

    public PersonService(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    public List<Person> findAll() {
        return personDAO.index();
    }

    public Person findById(long personId) {
        return personDAO.show(personId);
    }

    public List<Book> getBooksByPersonId(long personId) {
        return personDAO.getBooksByPersonId(personId);
    }

    public void save(Person person) {
        personDAO.save(person);
    }

    public void update(long id, Person person) {
        personDAO.update(id, person);
    }

    public void delete(long id) {
        personDAO.delete(id);
    }
}
