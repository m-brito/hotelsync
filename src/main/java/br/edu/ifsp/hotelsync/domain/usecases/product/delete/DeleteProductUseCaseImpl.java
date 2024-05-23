package br.edu.ifsp.hotelsync.domain.usecases.product.delete;

import br.edu.ifsp.hotelsync.domain.entities.product.Product;
import br.edu.ifsp.hotelsync.domain.persistence.dao.ProductDAO;

import java.util.NoSuchElementException;

public class DeleteProductUseCaseImpl implements DeleteProductUseCase {

    private final ProductDAO repository;

    public DeleteProductUseCaseImpl(ProductDAO repository) {
        this.repository = repository;
    }


    @Override
    public void deleteById(RequestModel request) {
        Long id = request.id();

        Product product = repository.findOneByKey(id).orElseThrow(
                () -> new NoSuchElementException("Product of id " + id + " not found")
        );

        product.deactivate();
        repository.update(product);
    }
}
