package md.vladdubceac.spring_learning.Project2Boot.services;

import md.vladdubceac.spring_learning.Project2Boot.models.Book;
import md.vladdubceac.spring_learning.Project2Boot.models.Person;
import md.vladdubceac.spring_learning.Project2Boot.repository.PersonRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PersonService {

    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> findAll() {
       return personRepository.findAll();
    }

    public Person findById(Long personId) {
        return personRepository.findById(personId).orElse(new Person());
    }

    public List<Book> getBooksByPersonId(Long personId) {
        return personRepository.getBooksByPersonId(personId);
    }

    public void save(Person person) {
        personRepository.save(person);
    }

    public void update(Long id, Person person) {
        Person founded = findById(id);
        if (founded.getId() == null) {
            personRepository.save(person);
        } else {
            founded.setFullName(person.getFullName());
            founded.setYearOfBirth(person.getYearOfBirth());
            personRepository.save(founded);
        }
    }

    public void delete(Long id) {
        personRepository.deleteById(id);
    }

    public Optional<Person> getPersonByFullName(String fullName) {
       return personRepository.findByFullName(fullName);
    }
}
