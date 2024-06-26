package br.edu.ifsp.hotelsync.domain.usecases.product.find;

import br.edu.ifsp.hotelsync.domain.entities.product.Product;
import br.edu.ifsp.hotelsync.domain.persistence.dao.ProductDao;

import java.util.Map;

public class FindAllProductByNameUseCaseImpl implements FindAllProductByNameUseCase {

    private final ProductDao repository;

    public FindAllProductByNameUseCaseImpl(ProductDao repository) {
        this.repository = repository;
    }

    @Override
    public Map<Long, Product> findAllByName(RequestModel requestModel) {
        return repository.findAllByName(requestModel.name());
    }
}
