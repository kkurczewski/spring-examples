package pl.kkurczewski.deser.value;

import pl.kkurczewski.deser.value.dto.ValueObject;
import pl.kkurczewski.deser.value.dto.Wrapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.net.URI;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@WebFluxTest(ValueObjectController.class)
class ValueObjectControllerTest {

    @Autowired
    private WebTestClient webClient;

    @Test
    void shouldEchoWrapperRequest() {
        Wrapper wrapper = new Wrapper(ValueObject.from("foo"), ValueObject.from("bar"));

        webClient
                .post()
                .uri(URI.create("/wrapper"))
                .contentType(APPLICATION_JSON)
                .bodyValue(wrapper)
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBody(Wrapper.class)
                .isEqualTo(wrapper)
                .returnResult();
    }

    @Test
    void validateJsonWrapperFormat() {
        Wrapper wrapper = new Wrapper(ValueObject.from("foo"), ValueObject.from("bar"));

        webClient
                .post()
                .uri(URI.create("/wrapper"))
                .contentType(APPLICATION_JSON)
                .bodyValue(wrapper)
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBody()
                .json(normalize("{'first':'foo','second':'bar'}"));
    }

    private String normalize(String json) {
        return json.replaceAll("'", "\"");
    }
}