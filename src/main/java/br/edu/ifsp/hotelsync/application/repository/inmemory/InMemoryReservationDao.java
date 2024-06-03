package br.edu.ifsp.hotelsync.application.repository.inmemory;

import br.edu.ifsp.hotelsync.domain.entities.reservation.Reservation;
import br.edu.ifsp.hotelsync.domain.persistence.dao.ReservationDao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class InMemoryReservationDao implements ReservationDao {
    private static final Map<Long, Reservation> reservations = new HashMap<>();
    private static long id = 0;

    @Override
    public Long save(Reservation reservation) {
        reservations.put(++id, reservation);
        reservation.setId(id);
        return id;
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
    public boolean existsByKey(Long id) {
        return reservations.containsKey(id);
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

    @Override
    public boolean cancelReservation(Long reservationId) {
        Optional<Reservation> reservationOptional = findOneByKey(reservationId);
        if (reservationOptional.isPresent()) {
            Reservation reservation = reservationOptional.get();
            reservation.cancelReservation();
            update(reservation);
            return true;
        }
        return false;
    }

    @Override
    public boolean checkIn(Long reservationId) {
        Optional<Reservation> reservationOptional = findOneByKey(reservationId);
        if (reservationOptional.isPresent()) {
            Reservation reservation = reservationOptional.get();
            reservation.checkIn();
            update(reservation);
            return true;
        }
        return false;
    }

    @Override
    public boolean checkOut(Long reservationId, String paymentMethod) {
        Optional<Reservation> reservationOptional = findOneByKey(reservationId);
        if (reservationOptional.isPresent()) {
            Reservation reservation = reservationOptional.get();
            reservation.checkOut(paymentMethod);
            update(reservation);
            return true;
        }
        return false;
    }
}
