package br.edu.ifsp.hotelsync.domain.usecases.product.create;

import br.edu.ifsp.hotelsync.domain.entities.product.Product;
import br.edu.ifsp.hotelsync.domain.persistence.dao.ProductDAO;

public class CreateProductUseCaseImpl implements CreateProductUseCase{

    private final ProductDAO repository;

    public CreateProductUseCaseImpl(ProductDAO repository) {
        this.repository = repository;
    }

    @Override
    public Long createProduct(RequestModel request) {
        Product product = Product.createProduct(request.description(), request.price());

        return repository.save(product);
    }
}
