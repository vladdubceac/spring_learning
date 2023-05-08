package md.vladdubceac.spring_learning.Project2Boot.repository;

import md.vladdubceac.spring_learning.Project2Boot.models.Book;
import md.vladdubceac.spring_learning.Project2Boot.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    @Query("SELECT b FROM Book b WHERE b.personId= :ownerId")
    List<Book> getBooksByPersonId(@Param("ownerId")Long personId);

    Optional<Person> findByFullName(String fullName);
}
