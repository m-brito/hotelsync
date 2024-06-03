package br.edu.ifsp.hotelsync.domain.entities.reservation;

import br.edu.ifsp.hotelsync.domain.entities.product.Product;

import java.util.Objects;

public record ConsumedProduct(Product product, int quantity) {

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConsumedProduct that = (ConsumedProduct) o;
        return Objects.equals(product, that.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product);
    }
}
