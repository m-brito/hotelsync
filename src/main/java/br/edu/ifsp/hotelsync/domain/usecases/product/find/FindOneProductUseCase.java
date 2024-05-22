package br.edu.ifsp.hotelsync.domain.usecases.product.find;

import br.edu.ifsp.hotelsync.domain.entities.product.Product;

public interface FindOneProductUseCase {

    record RequestModel(Long id) {}

    Product findOneById(RequestModel request);

}
