package md.vladdubceac.spring_learning.task_1.controllers;

import md.vladdubceac.spring_learning.task_1.dao.BookDAO;
import md.vladdubceac.spring_learning.task_1.models.Book;
import md.vladdubceac.spring_learning.task_1.models.Person;
import md.vladdubceac.spring_learning.task_1.services.BookService;
import md.vladdubceac.spring_learning.task_1.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BooksController {

    private final BookService bookService;
    private final PersonService personService;

    @Autowired
    public BooksController(BookService bookService, PersonService personService) {
        this.bookService = bookService;
        this.personService = personService;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("books", bookService.findAll());
        return "books/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") long id, Model model, @ModelAttribute("person") Person person) {
        model.addAttribute("book", bookService.findById(id));

        Optional<Person> bookOwner = bookService.getBookOwner(id);

        if (bookOwner.isPresent()) {
            model.addAttribute("owner", bookOwner.get());
        } else {
            model.addAttribute("people", personService.findAll());
        }

        return "books/show";
    }

    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book) {

        return "books/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "books/new";
        }

        bookService.save(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") long id) {
        model.addAttribute("book", bookService.findById(id));
        return "books/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult, @PathVariable("id") long id) {
        if (bindingResult.hasErrors()) {
            return "books/edit";
        }
        bookService.update(id, book);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") long id) {
        bookService.delete(id);
        return "redirect:/books";
    }

    @PatchMapping("/{id}/release")
    public String release(@PathVariable("id") long id) {
        bookService.release(id);
        return "redirect:/books/" + id;
    }

    @PatchMapping("/{id}/assign")
    public String assign(@PathVariable("id") long id, @ModelAttribute("person") Person selectedPerson) {
        bookService.assign(id, selectedPerson);
        return "redirect:/books/" + id;
    }
}
