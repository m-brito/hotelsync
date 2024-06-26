package br.edu.ifsp.hotelsync.application.repository.inmemory;

import br.edu.ifsp.hotelsync.domain.entities.reservation.Reservation;
import br.edu.ifsp.hotelsync.domain.entities.reservation.ReservationStatus;
import br.edu.ifsp.hotelsync.domain.persistence.dao.ReservationDao;
import br.edu.ifsp.hotelsync.domain.entities.report.records.CheckInReport;
import br.edu.ifsp.hotelsync.domain.entities.report.records.CheckOutReport;
import br.edu.ifsp.hotelsync.domain.entities.report.records.DailyOccupationReport;
import br.edu.ifsp.hotelsync.domain.entities.report.records.FinancialReport;
import br.edu.ifsp.hotelsync.domain.persistence.dao.RoomDao;

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
    public Map<Long, Reservation> findAllByOwner(String name) {
        return Map.copyOf(reservations);
    }

    @Override
    public Reservation resultSetToEntity(ResultSet resultSet) throws SQLException {
        return null;
    }

    @Override
    public DailyOccupationReport getDailyOccupationReport(LocalDate initialDate, LocalDate finalDate,
                                                          RoomDao roomRepository) {
        Map<LocalDate, Double> reports = new HashMap<>();
        int totalRooms = roomRepository.getTotalRooms();

        for (LocalDate date = initialDate; !date.isAfter(finalDate); date = date.plusDays(1)) {
            LocalDate currentDate = date;
            int occupiedRooms = (int) Map.copyOf(reservations).values().stream()
                    .filter(reservation -> {
                        LocalDate checkInDate = reservation.getCheckInDate();
                        LocalDate checkOutDate = reservation.getCheckOutDate();
                        if (checkInDate == null || checkOutDate == null) {
                            return false;
                        }
                        return !currentDate.isBefore(checkInDate) && !currentDate.isAfter(checkOutDate);
                    })
                    .count();

            double occupiedPercentage = (double) (occupiedRooms * 100) / totalRooms;

            reports.put(date, occupiedPercentage);
        }

        return new DailyOccupationReport(reports);
    }


    @Override
    public CheckInReport getCheckInReport(LocalDate initialDate, LocalDate finalDate) {
        Map<LocalDate, Integer> reports = new HashMap<>();

        for (LocalDate date = initialDate; !date.isAfter(finalDate); date = date.plusDays(1)) {

            LocalDate currentDate = date;
            int counter = (int) Map.copyOf(reservations).values().stream()
                    .filter(reservation -> reservation.getCheckInDate().equals(currentDate))
                    .count();

            reports.put(date, counter);
        }

        return new CheckInReport(reports);
    }

    @Override
    public CheckOutReport getCheckOutReport(LocalDate initialDate, LocalDate finalDate) {
        Map<LocalDate, Integer> reports = new HashMap<>();

        for (LocalDate date = initialDate; !date.isAfter(finalDate); date = date.plusDays(1)) {
            LocalDate currentDate = date;
            int counter = (int) Map.copyOf(reservations).values().stream()
                    .filter(reservation -> reservation.getCheckOutDate().equals(currentDate))
                    .count();

            reports.put(date, counter);
        }

        return new CheckOutReport(reports);
    }

    @Override
    public FinancialReport getFinancialReport(LocalDate initialDate, LocalDate finalDate) {
        Map<LocalDate, Double> reports = new HashMap<>();

        for (LocalDate date = initialDate; !date.isAfter(finalDate); date = date.plusDays(1)) {
            LocalDate currentDate = date;
            double dailyTotal = Map.copyOf(reservations).values().stream()
                    .filter(reservation -> reservation.getCheckOutDate().equals(currentDate) &&
                            reservation.getReservationStatus() == ReservationStatus.FINISHED)
                    .count();

            reports.put(date, dailyTotal);
        }

        return new FinancialReport(reports);
    }
}
