package br.edu.ifsp.hotelsync.domain.usecases.product.update;

import br.edu.ifsp.hotelsync.domain.entities.product.Product;
import br.edu.ifsp.hotelsync.domain.persistence.dao.ProductDAO;
import br.edu.ifsp.hotelsync.domain.usecases.product.ProductInputRequestValidator;
import br.edu.ifsp.hotelsync.domain.usecases.utils.Notification;
import br.edu.ifsp.hotelsync.domain.usecases.utils.Validator;

import java.util.NoSuchElementException;

public class UpdateProductUseCaseImpl implements UpdateProductUseCase {

    private final ProductDAO repository;

    public UpdateProductUseCaseImpl(ProductDAO repository) {
        this.repository = repository;
    }

    @Override
    public void updateProduct(RequestModel requestModel) {
        if(!repository.existsByKey(requestModel.id()))
            throw new NoSuchElementException("Product of id " + requestModel.id() + " not found");

        Product product = new Product(requestModel.id(), requestModel.description(), requestModel.price());

        Validator<Product> validator = new ProductInputRequestValidator();
        Notification notification = validator.validate(product);

        if(notification.hasErrors())
            throw new IllegalArgumentException(notification.getEerrorMessage());

        repository.update(product);
    }
}
