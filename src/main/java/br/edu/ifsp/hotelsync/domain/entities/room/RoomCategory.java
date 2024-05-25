package br.edu.ifsp.hotelsync.domain.entities.room;

public enum RoomCategory {
    STANDARD("", 0.0),
    EXECUTIVE("", 0.0),
    DELUXE("", 0.0);

    private final String description;
    private final double basePrice;

    RoomCategory(String description, double basePrice) {
        this.description = description;
        this.basePrice = basePrice;
    }

    public String getDescription() {
        return description;
    }

    public double getBasePrice() {
        return basePrice;
    }

    @Override
    public String toString() {
        return description;
    }
}
