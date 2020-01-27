package pl.kkurczewski.deser.value

import pl.kkurczewski.deser.value.dto.Wrapper
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class ValueObjectController {
    @PostMapping("/wrapper")
    fun wrapper(@RequestBody wrapper: Wrapper) = wrapper
}