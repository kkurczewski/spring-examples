package pl.kkurczewski.json.user

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.test.web.reactive.server.WebTestClient
import pl.kkurczewski.json.user.dto.Address
import pl.kkurczewski.json.user.dto.User
import java.net.URI

@WebFluxTest(UserController::class)
class UserControllerTest(@Autowired val webClient: WebTestClient) {

    @Disabled
    @Test
    fun shouldEchoRequest() {
        val user = User("James", "Bond", Address("REDACTED", "Great Britain"))
        val body = webClient
                .post()
                .uri(URI.create("/user"))
                .bodyValue(user)
                .exchange()
                .expectStatus()
                .is2xxSuccessful
                .expectBody(User::class.java)
                .returnResult()
                .responseBody

        assertThat(body).isEqualTo(user)
    }

    @Disabled
    @Test
    fun validateJsonFormat() {
        val user = User("James", "Bond", Address("REDACTED", "Great Britain"))
        webClient
                .post()
                .uri(URI.create("/user"))
                .bodyValue(user)
                .exchange()
                .expectStatus()
                .is2xxSuccessful
                .expectBody()
                .json(normalize("{'name':'James','surname':'Bond','street':'REDACTED','city':'Great Britain'}"))
    }

    private fun normalize(json: String): String {
        return json.replace("'".toRegex(), "\"")
    }
}