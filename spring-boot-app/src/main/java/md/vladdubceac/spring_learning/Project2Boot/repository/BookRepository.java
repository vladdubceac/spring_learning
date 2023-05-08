package md.vladdubceac.spring_learning.Project2Boot.repository;

import md.vladdubceac.spring_learning.Project2Boot.models.Book;
import md.vladdubceac.spring_learning.Project2Boot.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {
    @Query("SELECT p FROM Book b JOIN Person p ON b.personId = p.id WHERE b.id = :bookId")
    Optional<Person> getBookOwner(@Param("bookId") long bookId);

    @Modifying
    @Query("UPDATE Book SET personId = NULL WHERE id = :bookId")
    void release(@Param("bookId") long bookId);

    @Modifying
    @Query(value = "UPDATE Book b SET b.personId = :personId WHERE b.id = :bookId")
    void assign(@Param("bookId") long bookId, @Param("personId") long personId);
}
