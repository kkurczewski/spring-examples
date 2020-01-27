package kkurczewski.deser.value;

import kkurczewski.deser.value.dto.Wrapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ValueObjectController {

    @PostMapping("/wrapper")
    public Wrapper wrapper(@RequestBody Wrapper wrapper) {
        return wrapper;
    }
}