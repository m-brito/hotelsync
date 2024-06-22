package br.edu.ifsp.hotelsync.domain.persistence.dao;

import br.edu.ifsp.hotelsync.domain.entities.product.Product;
import br.edu.ifsp.hotelsync.domain.entities.reservation.ConsumedProduct;
import br.edu.ifsp.hotelsync.domain.persistence.util.Dao;

import java.util.List;

public interface ProductDao extends Dao<Long, Product> {
    List<ConsumedProduct> findAllByIdReservation(Long id);
}
