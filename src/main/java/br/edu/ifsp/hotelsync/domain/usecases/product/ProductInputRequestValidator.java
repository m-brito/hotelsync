package br.edu.ifsp.hotelsync.domain.usecases.product;

import br.edu.ifsp.hotelsync.domain.entities.product.Product;
import br.edu.ifsp.hotelsync.domain.usecases.utils.Notification;
import br.edu.ifsp.hotelsync.domain.usecases.utils.Validator;

public class ProductInputRequestValidator extends Validator<Product> {


    @Override
    public Notification validate(Product product) {
        Notification notification = new Notification();
        if(product == null) {
            notification.addError("Product is null");
            return notification;
        }
        if(Validator.isNullOrEmpty(product.getDescription()))
            notification.addError("Description is null or empty");
        if(product.getPrice() < 0)
            notification.addError("Price cannot be a negative value");

        return notification;
    }
}