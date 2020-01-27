package pl.kkurczewski.deser.greet

import pl.kkurczewski.deser.greet.dto.Greeting
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class GreetingController {
    @PostMapping("/greet")
    fun greeting(@RequestBody greeting: Greeting) = greeting
}