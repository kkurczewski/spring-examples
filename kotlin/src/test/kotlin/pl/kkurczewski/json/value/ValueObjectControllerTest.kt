package pl.kkurczewski.json.value

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.http.MediaType
import org.springframework.test.web.reactive.server.WebTestClient
import pl.kkurczewski.json.value.dto.ValueObject
import pl.kkurczewski.json.value.dto.Wrapper
import java.net.URI

@WebFluxTest(ValueObjectController::class)
class ValueObjectControllerTest(@Autowired val webClient: WebTestClient) {

    @Test
    fun shouldEchoWrapperRequest() {
        val wrapper = Wrapper(ValueObject("foo"), ValueObject("bar"))
        val body = webClient
                .post()
                .uri(URI.create("/wrapper"))
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(wrapper)
                .exchange()
                .expectStatus()
                .is2xxSuccessful
                .expectBody(Wrapper::class.java)
                .returnResult()
                .responseBody

        assertThat(body).isEqualTo(wrapper)
    }

    @Test
    fun validateJsonWrapperFormat() {
        val wrapper = Wrapper(ValueObject("foo"), ValueObject("bar"))
        webClient
                .post()
                .uri(URI.create("/wrapper"))
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(wrapper)
                .exchange()
                .expectStatus()
                .is2xxSuccessful
                .expectBody()
                .json(normalize("{'first':'foo','second':'bar'}"))
    }

    private fun normalize(json: String): String {
        return json.replace("'".toRegex(), "\"")
    }
}