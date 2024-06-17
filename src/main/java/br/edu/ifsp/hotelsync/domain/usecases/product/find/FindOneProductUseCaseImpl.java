package br.edu.ifsp.hotelsync.domain.usecases.product.find;

import br.edu.ifsp.hotelsync.domain.entities.product.Product;
import br.edu.ifsp.hotelsync.domain.persistence.dao.ProductDao;

import java.util.NoSuchElementException;

public class FindOneProductUseCaseImpl implements FindOneProductUseCase {
    private final ProductDao repository;

    public FindOneProductUseCaseImpl(ProductDao repository) {
        this.repository = repository;
    }

    @Override
    public Product findOneById(RequestModel request) {
        return repository.findOneByKey(request.id()).orElseThrow(
                () -> new NoSuchElementException("CreateProduct of id " + request.id() + " not found")
        );
    }
}
