package md.vladdubceac.spring_learning.FirstSecurityApp.models;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name="person")
public class Person {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Name must not be empty")
    @Size(min=2, max = 100, message = "Name must contain between 2 and 100 symbols")
    @Column(name="username")
    private String username;

    @Min(value = 1900, message = "Year of birth must be greater than 1900")
    @Column(name="year_of_birth")
    private short yearOfBirth ;

    @Column(name="password")
    private String password;

    public Person() {
    }

    public Person(@NotEmpty(message = "Name must not be empty") @Size(min = 2, max = 100, message = "Name must contain between 2 and 100 symbols") String username, @Min(value = 1900, message = "Year of birth must be greater than 1900") short yearOfBirth) {
        this.username = username;
        this.yearOfBirth = yearOfBirth;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String useraname) {
        this.username = useraname;
    }

    public short getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(short yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", yearOfBirth=" + yearOfBirth +
                '}';
    }
}
