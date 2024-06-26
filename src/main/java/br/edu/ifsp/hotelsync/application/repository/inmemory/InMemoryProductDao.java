package br.edu.ifsp.hotelsync.application.repository.inmemory;

import br.edu.ifsp.hotelsync.domain.entities.product.Product;
import br.edu.ifsp.hotelsync.domain.entities.reservation.ConsumedProduct;
import br.edu.ifsp.hotelsync.domain.persistence.dao.ProductDao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class InMemoryProductDao implements ProductDao {
    private static final Map<Long, Product> products = new HashMap<>();
    private static long id = 0;

    @Override
    public Long save(Product product) {
        products.put(++id, product);
        product.setId(id);
        return id;
    }

    @Override
    public void update(Product product) {
        products.put(product.getId(), product);
    }

    @Override
    public Optional<Product> findOneByKey(Long id) {
        return Optional.ofNullable(products.get(id));
    }

    @Override
    public boolean existsByKey(Long id) {
        return products.containsKey(id);
    }

    @Override
    public Map<Long, Product> findAll() {
        return Map.copyOf(products);
    }

    @Override
    public Product resultSetToEntity(ResultSet resultSet) throws SQLException {
        return null;
    }

    @Override
    public List<ConsumedProduct> findAllByIdReservation(Long id) {
        return List.of();
    }

    @Override
    public Map<Long, Product> findAllByName(String name) {
        return Map.of();
    }
}
