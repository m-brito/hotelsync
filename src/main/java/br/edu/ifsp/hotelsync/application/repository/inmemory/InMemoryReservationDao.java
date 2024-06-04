package br.edu.ifsp.hotelsync.application.repository.inmemory;

import br.edu.ifsp.hotelsync.domain.entities.reservation.Reservation;
import br.edu.ifsp.hotelsync.domain.persistence.dao.ReservationDao;
import br.edu.ifsp.hotelsync.domain.usecases.reports.records.CheckInReport;
import br.edu.ifsp.hotelsync.domain.usecases.reports.records.CheckOutReport;
import br.edu.ifsp.hotelsync.domain.usecases.reports.records.DailyOccupationReport;
import br.edu.ifsp.hotelsync.domain.usecases.reports.records.FinancialReport;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
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
    public DailyOccupationReport getDailyOccupationReport(LocalDate initialDate, LocalDate finalDate) {
//        Map.copyOf(reservations).entrySet().stream().filter(
//                e -> !e.getValue().getCheckInDate().isBefore(initialDate) &&
//                        !e.getValue().getCheckOutDate().isAfter(finalDate));
        return null;
    }

    @Override
    public CheckInReport getCheckInReport(LocalDate initialDate, LocalDate finalDate) {
        return null;
    }

    @Override
    public CheckOutReport getCheckOutReport(LocalDate initialDate, LocalDate finalDate) {
        return null;
    }

    @Override
    public FinancialReport getFinancialReport(LocalDate initialDate, LocalDate finalDate) {
        return null;
    }
}
