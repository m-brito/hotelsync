package br.edu.ifsp.hotelsync.application.repository.inmemory;

import br.edu.ifsp.hotelsync.domain.entities.reservation.Reservation;
import br.edu.ifsp.hotelsync.domain.persistence.dao.ReservationDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class InMemoryReservationDAO implements ReservationDAO {
    private static final Map<Long, Reservation> reservations = new HashMap<>();
    private static long id = 0;

    @Override
    public void save(Reservation reservation) {
        reservations.put(++id, reservation);
        reservation.setId(id);
    }

    @Override
    public void update(Reservation reservation) {
        reservations.put(reservation.getId(), reservation);
    }

    @Override
    public Optional<Reservation> findOneByKey(Long id) {
        return Optional.ofNullable(reservations.get(id));
    }

    @Override
    public Map<Long, Reservation> findAll() {
        return Map.copyOf(reservations);
    }

    @Override
    public void deleteByKey(Long id) {
        reservations.remove(id);
    }

    @Override
    public Reservation resultSetToEntity(ResultSet resultSet) throws SQLException {
        return null;
    }
}
