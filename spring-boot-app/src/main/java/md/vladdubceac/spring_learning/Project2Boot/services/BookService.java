package md.vladdubceac.spring_learning.Project2Boot.services;

import md.vladdubceac.spring_learning.Project2Boot.dao.BookDAO;
import md.vladdubceac.spring_learning.Project2Boot.models.Book;
import md.vladdubceac.spring_learning.Project2Boot.models.Person;
import md.vladdubceac.spring_learning.Project2Boot.repository.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public Book findById(long id) {
        return bookRepository.findById(id).orElse(new Book());
    }

    public Optional<Person> getBookOwner(long id) {
        return bookRepository.getBookOwner(id);
//        return bookDAO.getBookOwner(id);
    }

    public void save(Book book) {
        bookRepository.save(book);
    }

    public void update(long id, Book book) {
        Book founded = bookRepository.findById(id).orElse(new Book());
        if (founded.getId() <= 0) {
            bookRepository.save(book);
        } else {
            founded.setAuthor(book.getAuthor());
            founded.setTitle(book.getTitle());
            founded.setYear(book.getYear());
            bookRepository.save(founded);
        }
    }

    public void delete(long id) {
        bookRepository.deleteById(id);
    }

    public void release(long id) {
        bookRepository.release(id);
    }

    public void assign(long id, Person selectedPerson) {
//        Book book = new Book();
//        book.setId(id);
//        book.setPersonId(selectedPerson.getId());
//        bookRepository.saveAndFlush(book);
        bookRepository.assign(id, selectedPerson.getId());
    }
}
