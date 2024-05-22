package br.edu.ifsp.hotelsync.domain.entities.product;

public class Product {
    private Long id;
    private String description;
    private double price;
    private boolean isActive = true;


    public Product(Long id, String description, double price) {
        this.id = id;
        this.description = description;
        this.price = price;
    }

    public Product(String description, double price) {
        this.description = description;
        this.price = price;
    }

    public void deactivate(){
        isActive = false;
    }

    public void activate(){
        isActive = true;
    }

    public boolean isActive() {
        return isActive;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
