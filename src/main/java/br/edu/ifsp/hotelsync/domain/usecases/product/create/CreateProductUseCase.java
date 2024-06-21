package br.edu.ifsp.hotelsync.domain.usecases.product.create;

import br.edu.ifsp.hotelsync.domain.entities.product.Product;

public interface CreateProductUseCase {
    record RequestModel(String description, double price) {}

    void execute(Product newProduct);

    Long createProduct(RequestModel request);
}
