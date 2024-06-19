package br.edu.ifsp.hotelsync.domain.entities.reservation;

public enum Payment {
    CASH("Cash"),
    CREDIT_CARD("Credit Card"),
    DEBIT("Debit");

    private final String description;

    Payment(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return description;
    }
}