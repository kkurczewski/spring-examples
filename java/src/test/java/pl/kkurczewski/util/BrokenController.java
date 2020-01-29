package pl.kkurczewski.util;

import org.springframework.stereotype.Controller;

@Controller
public class BrokenController {

    public BrokenController() {
        throw new RuntimeException("This test is not properly isolated");
    }
}
