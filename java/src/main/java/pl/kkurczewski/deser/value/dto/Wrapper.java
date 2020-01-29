package pl.kkurczewski.deser.value.dto;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Objects;

public class Wrapper {

    private final ValueObject first;
    private final ValueObject second;

    @JsonCreator
    public Wrapper(ValueObject first, ValueObject second) {
        this.first = first;
        this.second = second;
    }

    public ValueObject getFirst() {
        return first;
    }

    public ValueObject getSecond() {
        return second;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Wrapper wrapper = (Wrapper) o;
        return first.equals(wrapper.first);
    }

    @Override
    public int hashCode() {
        return Objects.hash(first);
    }
}
