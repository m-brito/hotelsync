package br.edu.ifsp.hotelsync.domain.persistence.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.Optional;

public interface DAO <K, T>{
    void save(T type);

    void update(T type);

    Optional<T> findOneByKey(K key);

    Map<K, T> findAll();

    void deleteByKey(K key);

    T resultSetToEntity(ResultSet resultSet) throws SQLException;
}

