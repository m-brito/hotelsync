package br.edu.ifsp.hotelsync.domain.entities.product;

import br.edu.ifsp.hotelsync.domain.usecases.utils.Notification;

import java.util.Objects;

public class Product {
    private Long id;
    private String description;
    private double price;
    private boolean isActive = true;


    public static Product createProduct(String description, double price){
        return new Product(description, price);
    }

    public static Product createProductWithId(Long id, String description, double price){
        return new Product(id, description, price);
    }

    public static Product createCompleteProduct(Long id, String description, double price, boolean isActive){
        return new Product(id, description, price, isActive);
    }

    public Product(Long id, String description, double price, boolean isActive) {
        this.id = id;
        this.description = description;
        this.price = price;
        this.isActive = isActive;
        validate();
    }

    private Product(Long id, String description, double price) {
        this.id = id;
        this.description = description;
        this.price = price;
        validate();
    }

    private Product(String description, double price) {
        this.description = description;
        this.price = price;
        validate();
    }

    private void validate() {
        ProductInputRequestValidator validator = new ProductInputRequestValidator();
        Notification notification = validator.validate(this);

        if (notification.hasErrors())
            throw new IllegalArgumentException(notification.getErrorMessage());
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return String.format("%s - R$ %.2f", description, price);
    }
}
