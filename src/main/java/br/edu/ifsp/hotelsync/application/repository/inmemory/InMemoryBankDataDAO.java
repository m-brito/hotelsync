package br.edu.ifsp.hotelsync.application.repository.inmemory;

import br.edu.ifsp.hotelsync.domain.entities.guest.BankData;
import br.edu.ifsp.hotelsync.domain.persistence.dao.BankDataDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class InMemoryBankDataDAO implements BankDataDAO {
    private static final Map<Long, BankData> bankDatas = new HashMap<>();
    private static long id = 0;

    @Override
    public Long save(BankData bankData) {
        bankDatas.put(++id, bankData);
        bankData.setId(id);
        return id;
    }

    @Override
    public void update(BankData bankData) {
        bankDatas.put(bankData.getId(), bankData);
    }

    @Override
    public Optional<BankData> findOneByKey(Long id) {
        return Optional.ofNullable(bankDatas.get(id));
    }

    @Override
    public boolean existsByKey(Long id) {
        return bankDatas.containsKey(id);
    }

    @Override
    public Map<Long, BankData> findAll() {
        return Map.copyOf(bankDatas);
    }

    @Override
    public void deleteByKey(Long id) {
        bankDatas.remove(id);
    }

    @Override
    public BankData resultSetToEntity(ResultSet resultSet) throws SQLException {
        return null;
    }
}
