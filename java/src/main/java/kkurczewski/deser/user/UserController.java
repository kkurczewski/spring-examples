package kkurczewski.deser.user;

import kkurczewski.deser.user.dto.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @PostMapping("/user")
    public User user(@RequestBody User user) {
        return user;
    }
}