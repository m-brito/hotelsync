package br.edu.ifsp.hotelsync.domain.entities.guest;

import java.util.Objects;

public class Cpf {
    private final String value;

    public Cpf(String value) {
        if (!isValid(value)) {
            throw new IllegalArgumentException("Invalid CPF");
        }
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    private boolean isValid(String value) {
        return value != null && value.matches("\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cpf cpf = (Cpf) o;
        return Objects.equals(value, cpf.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }
}
