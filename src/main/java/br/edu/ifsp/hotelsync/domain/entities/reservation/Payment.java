package br.edu.ifsp.hotelsync.domain.entities.reservation;

import java.time.LocalDate;

public class Payment {
    private Long id;
    private double value;
    private LocalDate date;
    private String method;

    public Payment(Long id, double value, LocalDate date, String method) {
        this.id = id;
        this.value = value;
        this.date = date;
        this.method = method;
    }

    public Payment(double value, LocalDate date, String method) {
        this.value = value;
        this.date = date;
        this.method = method;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}
