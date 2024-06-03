package br.edu.ifsp.hotelsync.domain.usecases.product.find;

import br.edu.ifsp.hotelsync.domain.entities.product.Product;
import br.edu.ifsp.hotelsync.domain.persistence.dao.ProductDao;

import java.util.Map;

public class FindAllProductUseCaseImpl implements FindAllProductUseCase {

    private final ProductDao repository;

    public FindAllProductUseCaseImpl(ProductDao repository) {
        this.repository = repository;
    }

    @Override
    public Map<Long, Product> findAll() {
        return repository.findAll();
    }
}
