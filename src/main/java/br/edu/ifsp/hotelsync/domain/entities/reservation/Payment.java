package br.edu.ifsp.hotelsync.domain.entities.reservation;

import java.time.LocalDate;

public record Payment(double value, LocalDate date, String method) {

    public double getValue() {
        return value;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getMethod() {
        return method;
    }
}
