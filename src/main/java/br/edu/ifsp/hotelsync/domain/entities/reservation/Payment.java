package br.edu.ifsp.hotelsync.domain.entities.reservation;

public enum Payment {
    CASH,
    CREDIT_CARD,
    DEBIT;

    @Override
    public String toString() {
        return switch (this) {
            case CASH -> "Cash";
            case CREDIT_CARD -> "Credit Card";
            case DEBIT -> "Debit";
        };
    }
}