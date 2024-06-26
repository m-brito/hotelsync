package br.edu.ifsp.hotelsync.domain.usecases.product.find;

import br.edu.ifsp.hotelsync.domain.entities.product.Product;

import java.util.Map;

public interface FindAllProductByNameUseCase {
    record RequestModel(String name) {}

    Map<Long, Product> findAllByName(RequestModel requestModel);

}
