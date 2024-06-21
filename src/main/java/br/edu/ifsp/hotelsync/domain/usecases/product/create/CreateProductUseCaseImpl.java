package br.edu.ifsp.hotelsync.domain.usecases.product.create;

import br.edu.ifsp.hotelsync.domain.entities.product.Product;
import br.edu.ifsp.hotelsync.domain.persistence.dao.ProductDao;

public class CreateProductUseCaseImpl implements CreateProductUseCase{

    private final ProductDao repository;

    public CreateProductUseCaseImpl(ProductDao repository) {
        this.repository = repository;
    }

    @Override
    public void execute(Product newProduct) {
        repository.save(newProduct);
    }

    @Override
    public Long createProduct(RequestModel request) {
        Product product = Product.createProduct(request.description(), request.price());

        return repository.save(product);
    }
}
