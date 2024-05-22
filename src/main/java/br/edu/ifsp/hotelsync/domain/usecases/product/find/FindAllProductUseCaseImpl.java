package br.edu.ifsp.hotelsync.domain.usecases.product.find;

import br.edu.ifsp.hotelsync.domain.entities.product.Product;
import br.edu.ifsp.hotelsync.domain.persistence.dao.ProductDAO;

import java.util.Map;

public class FindAllProductUseCaseImpl implements FindAllProductUseCase {

    private final ProductDAO repository;

    public FindAllProductUseCaseImpl(ProductDAO repository) {
        this.repository = repository;
    }

    @Override
    public Map<Long, Product> findAll() {
        return repository.findAll();
    }
}
