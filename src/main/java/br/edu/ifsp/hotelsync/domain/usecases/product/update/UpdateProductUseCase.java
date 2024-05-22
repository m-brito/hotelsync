package br.edu.ifsp.hotelsync.domain.usecases.product.update;

public interface UpdateProductUseCase {
    record RequestModel(Long id, String description, double price) {}

    void updateProduct(RequestModel requestModel);

}
