package br.edu.ifsp.hotelsync.domain.entities.guest;

import java.util.Objects;

public record Phone(String value) {

    public Phone {
        if (!isValid(value)) {
            throw new IllegalArgumentException("Invalid phone number");
        }
    }

    public String getValue() {
        return value;
    }

    private boolean isValid(String value) {
        return value != null && value.matches("\\(\\d{2}\\) \\d{4,5}-\\d{4}");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Phone phone = (Phone) o;
        return Objects.equals(value, phone.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }

    @Override
    public String toString() {
        return value;
    }
}