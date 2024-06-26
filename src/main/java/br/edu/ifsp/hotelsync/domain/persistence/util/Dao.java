package br.edu.ifsp.hotelsync.domain.persistence.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.Optional;

public interface Dao<K, T>{
    K save(T type);

    void update(T type);

    Optional<T> findOneByKey(K id);

    boolean existsByKey(K id);

    Map<K, T> findAll();
    
    T resultSetToEntity(ResultSet resultSet) throws SQLException;
}

