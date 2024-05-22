package br.edu.ifsp.hotelsync.application.repository.inmemory;

import br.edu.ifsp.hotelsync.domain.entities.product.Product;
import br.edu.ifsp.hotelsync.domain.persistence.dao.ProductDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class InMemoryProductDAO implements ProductDAO {
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
    public void deleteByKey(Long id) {
        products.remove(id);
    }

    @Override
    public Product resultSetToEntity(ResultSet resultSet) throws SQLException {
        return null;
    }
}
