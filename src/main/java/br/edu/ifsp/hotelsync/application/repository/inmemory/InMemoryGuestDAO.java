package br.edu.ifsp.hotelsync.application.repository.inmemory;

import br.edu.ifsp.hotelsync.domain.entities.guest.Guest;
import br.edu.ifsp.hotelsync.domain.persistence.dao.GuestDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class InMemoryGuestDAO implements GuestDAO {
    private static final Map<Long, Guest> guests = new HashMap<>();
    private static long id = 0;

    @Override
    public Long save(Guest guest) {
        guests.put(++id, guest);
        guest.setId(id);
        return id;
    }

    @Override
    public void update(Guest guest) {
        guests.put(guest.getId(), guest);
    }

    @Override
    public Optional<Guest> findOneByKey(Long id) {
        return Optional.ofNullable(guests.get(id));
    }

    @Override
    public boolean existsByKey(Long id) {
        return guests.containsKey(id);
    }

    @Override
    public Map<Long, Guest> findAll() {
        return Map.copyOf(guests);
    }

    @Override
    public void deleteByKey(Long id) {
        guests.remove(id);
    }

    @Override
    public Guest resultSetToEntity(ResultSet resultSet) throws SQLException {
        return null;
    }
}
