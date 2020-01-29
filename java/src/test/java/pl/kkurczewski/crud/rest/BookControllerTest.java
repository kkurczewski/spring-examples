package pl.kkurczewski.crud.rest;

import pl.kkurczewski.crud.BookRepository;
import pl.kkurczewski.crud.dto.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.net.URI;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.doNothing;

@WebFluxTest(BookController.class)
class BookControllerTest {

    @Autowired
    private WebTestClient webClient;

    @MockBean
    private BookRepository bookRepository;

    @Test
    void addNewBook() {
        Book book = new Book("Hobbit", "J. R. R. Tolkien");
        doNothing().when(bookRepository).addBook(book);

        webClient
                .post()
                .uri(URI.create("/books"))
                .bodyValue(book)
                .exchange()
                .expectStatus()
                .isNoContent()
                .expectBody()
                .isEmpty();

        then(bookRepository).should().addBook(book);
    }

    @Test
    void getAllBooks() {
        Book book = new Book("Hobbit", "J. R. R. Tolkien");
        given(bookRepository.getBooks()).willReturn(List.of(book));

        webClient
                .get()
                .uri("/books")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBodyList(Book.class)
                .contains(book);
    }
}