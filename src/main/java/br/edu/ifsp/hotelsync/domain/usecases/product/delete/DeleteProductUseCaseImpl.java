package br.edu.ifsp.hotelsync.domain.usecases.product.delete;

import br.edu.ifsp.hotelsync.domain.entities.product.Product;
import br.edu.ifsp.hotelsync.domain.persistence.dao.ProductDao;

import java.util.NoSuchElementException;

public class DeleteProductUseCaseImpl implements DeleteProductUseCase {

    private final ProductDao repository;

    public DeleteProductUseCaseImpl(ProductDao repository) {
        this.repository = repository;
    }


    @Override
    public void deleteById(RequestModel request) {
        Long id = request.id();

        Product product = repository.findOneByKey(id).orElseThrow(
                () -> new NoSuchElementException("CreateProduct of id " + id + " not found")
        );

        product.deactivate();
        repository.update(product);
    }
}
