package br.edu.ifsp.hotelsync.domain.usecases.product.find;

import br.edu.ifsp.hotelsync.domain.entities.product.Product;

import java.util.Map;

public interface FindAllProductUseCase {

    Map<Long, Product> findAll();

}
