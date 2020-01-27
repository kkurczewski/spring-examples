package kkurczewski.deser.user;

import kkurczewski.deser.user.dto.Address;
import kkurczewski.deser.user.dto.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.net.URI;

@WebFluxTest(UserController.class)
class UserControllerTest {

    @Autowired
    private WebTestClient webClient;

    @Test
    void shouldEchoRequest() {
        User user = new User("James", "Bond", new Address("REDACTED", "Great Britain"));

        webClient
                .post()
                .uri(URI.create("/user"))
                .bodyValue(user)
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBody(User.class)
                .isEqualTo(user)
                .returnResult();
    }

    @Test
    void validateJsonFormat() {
        User user = new User("James", "Bond", new Address("REDACTED", "Great Britain"));

        webClient
                .post()
                .uri(URI.create("/user"))
                .bodyValue(user)
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBody()
                .json(normalize("{'name':'James','surname':'Bond','street':'REDACTED','city':'Great Britain'}"));
    }

    private String normalize(String json) {
        return json.replaceAll("'","\"");
    }
}