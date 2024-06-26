package br.edu.ifsp.hotelsync.application.repository.sqlite.dao;

import br.edu.ifsp.hotelsync.application.repository.sqlite.ConnectionFactory;
import br.edu.ifsp.hotelsync.domain.entities.product.Product;
import br.edu.ifsp.hotelsync.domain.entities.reservation.ConsumedProduct;
import br.edu.ifsp.hotelsync.domain.persistence.dao.ProductDao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class SqliteProductDao implements ProductDao {

    @Override
    public Long save(Product product) {
        String sql = "INSERT INTO Product(description, price, isActive) VALUES (?, ?, ?)";

        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setString(1, product.getDescription());
            stmt.setDouble(2, product.getPrice());
            stmt.setBoolean(3, product.isActive());
            stmt.execute();

            ResultSet resultSet = stmt.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getLong(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(Product product) {
        String sql = "UPDATE Product SET description = ?, price = ?, isActive = ? WHERE id = ?";

        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setString(1, product.getDescription());
            stmt.setDouble(2, product.getPrice());
            stmt.setBoolean(3, product.isActive());
            stmt.setLong(4, product.getId());
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Product> findOneByKey(Long id) {
        String sql = "SELECT * FROM Product WHERE id = ?";
        Product product = null;

        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                product = resultSetToEntity(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(product);
    }

    @Override
    public boolean existsByKey(Long id) {
        String sql = "SELECT count(*) FROM Product WHERE id = ?";
        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Map<Long, Product> findAll() {
        String sql = "SELECT * FROM Product";
        Map<Long, Product> products = new HashMap<>();

        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Product product = resultSetToEntity(rs);
                products.put(product.getId(), product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    @Override
    public List<ConsumedProduct> findAllByIdReservation(Long id) {
        List<ConsumedProduct> consumedProducts = new ArrayList<>();
        String sql = "SELECT p.*, cp.quantity FROM Product p JOIN ConsumedProduct cp ON p.id = cp.productId WHERE cp.reservationId = ?";

        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Product product = resultSetToEntity(rs);
                int quantity = rs.getInt("quantity");
                consumedProducts.add(new ConsumedProduct(product, quantity));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return consumedProducts;
    }

    @Override
    public Product resultSetToEntity(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong("id");
        String description = resultSet.getString("description");
        double price = resultSet.getDouble("price");
        boolean isActive = resultSet.getBoolean("isActive");

        return Product.createCompleteProduct(id, description, price, isActive);
    }
}
