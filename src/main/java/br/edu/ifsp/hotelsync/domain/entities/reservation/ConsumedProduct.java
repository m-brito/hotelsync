package br.edu.ifsp.hotelsync.domain.entities.reservation;

import br.edu.ifsp.hotelsync.domain.entities.product.Product;

public class ConsumedProduct {
    private final Product product;
    private int quantity;

    public ConsumedProduct(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

}
