package md.vladdubceac.spring_learning.task_1.services;

import md.vladdubceac.spring_learning.task_1.dao.BookDAO;
import md.vladdubceac.spring_learning.task_1.models.Book;
import md.vladdubceac.spring_learning.task_1.models.Person;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BookService {
    private final BookDAO bookDAO;

    public BookService(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    public List<Book> findAll() {
        return bookDAO.index();
    }

    public Book findById(long id) {
        return bookDAO.show(id);
    }

    public Optional<Person> getBookOwner(long id) {
        return bookDAO.getBookOwner(id);
    }

    public void save(Book book) {
        bookDAO.save(book);
    }

    public void update(long id, Book book) {
        bookDAO.update(id, book);
    }

    public void delete(long id) {
        bookDAO.delete(id);
    }

    public void release(long id) {
        bookDAO.release(id);
    }

    public void assign(long id, Person selectedPerson) {
        bookDAO.assign(id,selectedPerson);
    }
}
