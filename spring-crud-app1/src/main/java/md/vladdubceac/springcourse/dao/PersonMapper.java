package md.vladdubceac.springcourse.dao;

import md.vladdubceac.springcourse.models.Person;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonMapper implements RowMapper<Person> {
    @Override
    public Person mapRow(ResultSet resultSet, int i) throws SQLException {
        Person person = new Person();

        person.setId(resultSet.getInt("id"));
        person.setAge(resultSet.getShort("age"));
        person.setEmail(resultSet.getString("email"));
        person.setName(resultSet.getString("name"));

        return person;
    }
}
