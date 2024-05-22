package br.edu.ifsp.hotelsync.domain.usecases.product.create;

import br.edu.ifsp.hotelsync.domain.entities.product.Product;
import br.edu.ifsp.hotelsync.domain.persistence.dao.ProductDAO;
import br.edu.ifsp.hotelsync.domain.usecases.product.ProductInputRequestValidator;
import br.edu.ifsp.hotelsync.domain.usecases.utils.Notification;

import java.util.Objects;

public class CreateProductUseCaseImpl implements CreateProductUseCase{

    private final ProductDAO repository;

    public CreateProductUseCaseImpl(ProductDAO repository) {
        this.repository = repository;
    }

    @Override
    public Long createProduct(RequestModel request) {
        Product product = new Product(request.description(), request.price());

        ProductInputRequestValidator validator = new ProductInputRequestValidator();
        Notification notification = validator.validate(product);

        if (notification.hasErrors())
            throw new IllegalArgumentException(notification.getEerrorMessage());

        return repository.save(product);
    }
}
