package br.edu.ifsp.hotelsync.domain.usecases.product.find;

import br.edu.ifsp.hotelsync.domain.entities.product.Product;
import br.edu.ifsp.hotelsync.domain.persistence.dao.ProductDAO;

import java.util.NoSuchElementException;

public class FindOneProductUseCaseImpl implements FindOneProductUseCase {
    private final ProductDAO repository;

    public FindOneProductUseCaseImpl(ProductDAO repository) {
        this.repository = repository;
    }

    @Override
    public Product findOneById(RequestModel request) {
        return repository.findOneByKey(request.id()).orElseThrow(
                () -> new NoSuchElementException("Product of id " + request.id() + " not found")
        );
    }
}
