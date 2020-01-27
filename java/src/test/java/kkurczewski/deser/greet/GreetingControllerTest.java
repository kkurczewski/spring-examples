package kkurczewski.deser.greet;

import kkurczewski.deser.greet.dto.Greeting;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.net.URI;

@WebFluxTest(GreetingController.class)
class GreetingControllerTest {

    @Autowired
    private WebTestClient webClient;

    @Test
    void shouldEchoRequest() {
        Greeting greeting = new Greeting("Ahoj");

        webClient
                .post()
                .uri(URI.create("/greet"))
                .bodyValue(greeting)
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBody(Greeting.class)
                .isEqualTo(greeting)
                .returnResult();
    }

    @Test
    void validateJsonFormat() {
        Greeting greeting = new Greeting("Ahoj");

        webClient
                .post()
                .uri(URI.create("/greet"))
                .bodyValue(greeting)
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBody()
                .json(normalize("{'greeting':'Ahoj'}"));
    }

    private String normalize(String json) {
        return json.replaceAll("'", "\"");
    }

}