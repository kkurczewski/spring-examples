package kkurczewski.crud.rest;

import kkurczewski.crud.dto.Book;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.DEFINED_PORT;

@SpringBootTest(webEnvironment = DEFINED_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BookModuleTest {

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