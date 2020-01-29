package pl.kkurczewski.deser.user.dto;

import com.fasterxml.jackson.annotation.JsonUnwrapped;

import java.util.Objects;

public class User {

    private final String name;
    private final String surname;
    private final Address address;

    public User(String name, String surname, Address address) {
        this.surname = surname;
        this.name = name;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    @JsonUnwrapped
    public Address getAddress() {
        return address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return name.equals(user.name) &&
                surname.equals(user.surname) &&
                address.equals(user.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, address);
    }
}