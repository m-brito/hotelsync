package br.edu.ifsp.hotelsync.domain.usecases.product.delete;

public interface DeleteProductUseCase {

    record RequestModel(Long id) {}

    void deleteById(RequestModel request);
}
