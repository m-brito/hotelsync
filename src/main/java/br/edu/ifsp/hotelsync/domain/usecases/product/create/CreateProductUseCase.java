package br.edu.ifsp.hotelsync.domain.usecases.product.create;

public interface CreateProductUseCase {
    record RequestModel(String description, double price) {}

    Long create(RequestModel request);
}
