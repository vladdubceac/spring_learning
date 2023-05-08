package md.vladdubceac.spring_learning.Project2Boot.dao;

import md.vladdubceac.spring_learning.Project2Boot.models.Book;
import md.vladdubceac.spring_learning.Project2Boot.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class PersonDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Optional<Person> getPersonByFullName(String fullName) {
        return jdbcTemplate.query("SELECT * FROM Person WHERE full_Name = ?", new Object[]{fullName}, new BeanPropertyRowMapper<>(Person.class)).stream().findAny();
    }

    public List<Person> index() {
        return jdbcTemplate.query("SELECT * FROM Person", new BeanPropertyRowMapper<>(Person.class)).stream().collect(Collectors.toList());
    }

    public Person show(long id) {
        return jdbcTemplate.query("SELECT * FROM Person WHERE id = ? ", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class)).stream().findAny().orElse(null);
    }

    public List<Book> getBooksByPersonId(long id) {
        return jdbcTemplate.query("SELECT * FROM Book WHERE person_id = ?", new Object[]{id}, new BeanPropertyRowMapper<>(Book.class));
    }

    public void save(Person person) {
        jdbcTemplate.update("INSERT INTO Person(full_Name, year_Of_Birth) VALUES (?, ?)", person.getFullName(), person.getYearOfBirth());
    }

    public void update(long id, Person person) {
        jdbcTemplate.update("UPDATE Person SET full_Name = ? , year_Of_Birth = ? WHERE id = ?", person.getFullName(), person.getYearOfBirth(), id);
    }

    public void delete(long id) {
        jdbcTemplate.update("DELETE FROM Person WHERE id = ?", id);
    }
}
