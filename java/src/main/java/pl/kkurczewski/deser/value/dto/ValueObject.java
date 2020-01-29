package pl.kkurczewski.deser.value.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Objects;

public class ValueObject {

    private final String value;

    private ValueObject(String value) {
        this.value = value;
    }

    @JsonCreator
    public static ValueObject from(String value) {
        return new ValueObject(value);
    }

    @JsonValue
    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ValueObject that = (ValueObject) o;
        return value.equals(that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
