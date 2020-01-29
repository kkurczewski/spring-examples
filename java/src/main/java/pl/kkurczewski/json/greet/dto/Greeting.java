package pl.kkurczewski.json.greet.dto;

import java.util.Objects;

public class Greeting {

    private String greeting;

    private Greeting() {
        // mandatory no-arg constructor
    }

    public Greeting(String greeting) {
        this.greeting = greeting;
    }

    // accessor must start with `get`
    public String getGreeting() {
        return greeting;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Greeting greeting1 = (Greeting) o;
        return greeting.equals(greeting1.greeting);
    }

    @Override
    public int hashCode() {
        return Objects.hash(greeting);
    }
}