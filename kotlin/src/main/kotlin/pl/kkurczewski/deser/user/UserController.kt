package pl.kkurczewski.deser.user

import pl.kkurczewski.deser.user.dto.User
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController {
    @PostMapping("/user")
    fun user(@RequestBody user: User) = user
}