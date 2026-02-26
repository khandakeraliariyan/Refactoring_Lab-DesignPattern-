package edu.iutcs.cr.persons;

import java.io.Serializable;
import java.util.Objects;

/**
 * Value object wrapping a person's unique identifier.
 */
public class PersonId implements Serializable {

    private final String value;

    public PersonId(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PersonId personId)) return false;
        return Objects.equals(value, personId.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return value;
    }
}