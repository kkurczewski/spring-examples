package pl.kkurczewski.util;

import org.springframework.stereotype.Component;

@Component
public class BrokenComponent {

    public BrokenComponent() {
        throw new RuntimeException("This test is not properly isolated");
    }
}
