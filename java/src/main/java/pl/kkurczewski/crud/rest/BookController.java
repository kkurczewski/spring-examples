package kkurczewski.crud.rest;

import kkurczewski.crud.BookRepository;
import kkurczewski.crud.dto.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookRepository bookRepository;

    @Autowired
    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping
    public Flux<Book> getBooks() {
        return Flux.fromIterable(bookRepository.getBooks());
    }

    @PostMapping
    @ResponseStatus(NO_CONTENT)
    public void addBook(@RequestBody Book book) {
        bookRepository.addBook(book);
    }
}
