package pl.kkurczewski.deser.greet;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.kkurczewski.deser.greet.dto.Greeting;

@RestController
public class GreetingController {

    @PostMapping("/greet")
    public Greeting greeting(@RequestBody Greeting greeting) {
        return greeting;
    }
}