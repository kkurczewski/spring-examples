package pl.kkurczewski.deser.greet

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.test.web.reactive.server.WebTestClient
import pl.kkurczewski.deser.greet.dto.Greeting
import java.net.URI

@WebFluxTest(GreetingController::class)
class GreetingControllerTest(@Autowired val webClient: WebTestClient) {

    @Test
    fun shouldEchoRequest() {
        val greeting = Greeting("Ahoj")
        val body = webClient
                .post()
                .uri(URI.create("/greet"))
                .bodyValue(greeting)
                .exchange()
                .expectStatus()
                .is2xxSuccessful
                .expectBody(Greeting::class.java)
                .returnResult()
                .responseBody

        assertThat(body).isEqualTo(greeting)
    }

    @Test
    fun validateJsonFormat() {
        val greeting = Greeting("Ahoj")
        webClient
                .post()
                .uri(URI.create("/greet"))
                .bodyValue(greeting)
                .exchange()
                .expectStatus()
                .is2xxSuccessful
                .expectBody()
                .json(normalize("{'greeting':'Ahoj'}"))
    }

    private fun normalize(json: String): String {
        return json.replace("'".toRegex(), "\"")
    }
}