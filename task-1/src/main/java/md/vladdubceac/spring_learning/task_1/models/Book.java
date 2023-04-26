package md.vladdubceac.spring_learning.task_1.models;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Book {
    private long id;

    @NotEmpty(message = "Book should have a valid title")
    @Size(min = 2, max = 100, message = "Book title should have at least 3 characters")
    private String title;

    @NotEmpty(message = "Book should have an author with a non-empty name")
    @Size(min = 2, max = 100, message = "Name should be between 3 and 100 characters")
    private String author;

    @Min(value = 1500, message = "Year should be greater than 1500")
    private short year;

    public Book() {
    }

    public Book(String title, String author, short year) {
        this.title = title;
        this.author = author;
        this.year = year;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public short getYear() {
        return year;
    }

    public void setYear(short year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", year=" + year +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        if (year != book.year) return false;
        if (title != null ? !title.equals(book.title) : book.title != null) return false;
        return author != null ? author.equals(book.author) : book.author == null;
    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + (int) year;
        return result;
    }
}
