package kkurczewski.crud.rest;

import kkurczewski.crud.BookRepository;
import kkurczewski.crud.dto.Book;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.reactive.server.WebTestClient;

@WebFluxTest(BookController.class)
@Import(BookRepository.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BookControllerIT {

    @Autowired
    private WebTestClient webClient;

    @Test
    @Order(1)
    void addNewBook() {
        Book book = new Book("Hobbit", "J. R. R. Tolkien");

        webClient
                .post()
                .uri("/books")
                .bodyValue(book)
                .exchange()
                .expectStatus()
                .isNoContent()
                .expectBody()
                .isEmpty();
    }

    @Test
    @Order(2)
    void getAllBooks() {
        Book book = new Book("Hobbit", "J. R. R. Tolkien");

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