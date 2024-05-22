package br.edu.ifsp.hotelsync.domain.usecases.product.delete;

import br.edu.ifsp.hotelsync.domain.usecases.product.find.FindOneProductUseCase;

public interface DeleteProductUseCase {

    record RequestModel(Long id) {}

    void deleteById(RequestModel request);
}
