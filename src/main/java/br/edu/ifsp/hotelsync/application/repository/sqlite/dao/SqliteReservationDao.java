package br.edu.ifsp.hotelsync.application.repository.sqlite.dao;

import br.edu.ifsp.hotelsync.application.repository.sqlite.ConnectionFactory;
import br.edu.ifsp.hotelsync.domain.entities.guest.Guest;
import br.edu.ifsp.hotelsync.domain.entities.report.records.CheckInReport;
import br.edu.ifsp.hotelsync.domain.entities.report.records.CheckOutReport;
import br.edu.ifsp.hotelsync.domain.entities.report.records.DailyOccupationReport;
import br.edu.ifsp.hotelsync.domain.entities.report.records.FinancialReport;
import br.edu.ifsp.hotelsync.domain.entities.reservation.ConsumedProduct;
import br.edu.ifsp.hotelsync.domain.entities.reservation.Payment;
import br.edu.ifsp.hotelsync.domain.entities.reservation.Reservation;
import br.edu.ifsp.hotelsync.domain.entities.reservation.ReservationStatus;
import br.edu.ifsp.hotelsync.domain.entities.room.Room;
import br.edu.ifsp.hotelsync.domain.persistence.dao.ProductDao;
import br.edu.ifsp.hotelsync.domain.persistence.dao.ReservationDao;
import br.edu.ifsp.hotelsync.domain.persistence.dao.RoomDao;
import br.edu.ifsp.hotelsync.domain.persistence.dao.GuestDao;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class SqliteReservationDao implements ReservationDao {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public DailyOccupationReport getDailyOccupationReport(LocalDate initialDate, LocalDate finalDate, RoomDao roomRepository) {
        Map<LocalDate, Double> reports = new LinkedHashMap<>();

        for (LocalDate date = initialDate; !date.isAfter(finalDate); date = date.plusDays(1)) {
            reports.put(date, 0.0);
        }

        String sql = """
                    SELECT checkInDate, checkOutDate
                    FROM Reservation
                    WHERE checkInDate BETWEEN ? AND ?
                    AND checkOutDate IS NULL
                    OR checkInDate BETWEEN ? AND ? AND checkOutDate BETWEEN checkInDate+1 AND ?
                """;

        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setString(1, initialDate.format(formatter));
            stmt.setString(2, finalDate != null ? finalDate.format(formatter) : null);
            stmt.setString(3, initialDate.format(formatter));
            stmt.setString(4, finalDate != null ? finalDate.format(formatter) : null);
            stmt.setString(5, finalDate != null ? finalDate.format(formatter) : null);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String checkInDateString = rs.getString("date");
                Double occupancy_percentage = Double.parseDouble(rs.getString("occupancy_percentage"));

                if (checkInDateString != null) {
                    LocalDate date = LocalDate.parse(checkInDateString, formatter);
                    reports.put(date, occupancy_percentage);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new DailyOccupationReport(reports);
    }


    @Override
    public CheckInReport getCheckInReport(LocalDate initialDate, LocalDate finalDate) {
        Map<LocalDate, Integer> reports = new LinkedHashMap<>();

        String sql = "SELECT checkInDate, COUNT(*) as checkInCount FROM Reservation WHERE checkInDate BETWEEN ? AND ? GROUP BY checkInDate";

        for (LocalDate date = initialDate; !date.isAfter(finalDate); date = date.plusDays(1)) {
            reports.put(date, 0);
        }

        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setString(1, initialDate.format(formatter));
            stmt.setString(2, finalDate.format(formatter));

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                LocalDate checkInDate = LocalDate.parse(rs.getString("checkInDate"), formatter);
                int checkInCount = rs.getInt("checkInCount");

                reports.put(checkInDate, checkInCount);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new CheckInReport(reports);
    }

    @Override
    public CheckOutReport getCheckOutReport(LocalDate initialDate, LocalDate finalDate) {
        Map<LocalDate, Integer> reports = new LinkedHashMap<>();

        String sql = "SELECT checkOutDate FROM Reservation WHERE checkOutDate BETWEEN ? AND ?";

        for (LocalDate date = initialDate; !date.isAfter(finalDate); date = date.plusDays(1)) {
            reports.put(date, 0);
        }

        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setString(1, initialDate.format(formatter));
            stmt.setString(2, finalDate.format(formatter));

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                LocalDate checkOutDate = LocalDate.parse(rs.getString("checkOutDate"), formatter);
                reports.put(checkOutDate, reports.getOrDefault(checkOutDate, 0) + 1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new CheckOutReport(reports);
    }

    @Override
    public FinancialReport getFinancialReport(LocalDate initialDate, LocalDate finalDate) {
        Map<LocalDate, Double> reports = new LinkedHashMap<>();

        String sql = "SELECT checkOutDate, SUM(paymentValue) as paymentValue FROM Reservation WHERE checkOutDate BETWEEN ? AND ? AND reservationStatus = ? GROUP BY checkOutDate";

        for (LocalDate date = initialDate; !date.isAfter(finalDate); date = date.plusDays(1)) {
            reports.put(date, 0.0);
        }

        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setString(1, initialDate.format(formatter));
            stmt.setString(2, finalDate.format(formatter));
            stmt.setString(3, ReservationStatus.FINISHED.name());

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                LocalDate checkOutDate = LocalDate.parse(rs.getString("checkOutDate"), formatter);
                double paymentValue = rs.getDouble("paymentValue");

                reports.put(checkOutDate, paymentValue);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new FinancialReport(reports);
    }

    @Override
    public Long save(Reservation reservation) {
        String sql = "INSERT INTO Reservation(startDate, checkInDate, endDate, checkOutDate, reservationStatus, paymentMethod, paymentDate, paymentValue, roomId, ownerId) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setString(1, reservation.getStartDate() != null ? reservation.getStartDate().format(formatter) : null);
            stmt.setString(2, reservation.getCheckInDate() != null ? reservation.getCheckInDate().format(formatter) : null);
            stmt.setString(3, reservation.getEndDate() != null ? reservation.getEndDate().format(formatter) : null);
            stmt.setString(4, reservation.getCheckOutDate() != null ? reservation.getCheckOutDate().format(formatter) : null);
            stmt.setString(5, reservation.getReservationStatus().name());
            stmt.setString(6, reservation.getPayment() != null ? reservation.getPayment().name() : null);
            stmt.setString(7, LocalDate.now().format(formatter));
            stmt.setDouble(8, reservation.calculateTotalToPay());
            stmt.setLong(9, reservation.getRoom().getId());
            stmt.setLong(10, reservation.getOwner().getId());
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
    public void update(Reservation reservation) {
        String sql = "UPDATE Reservation SET startDate = ?, checkInDate = ?, endDate = ?, checkOutDate = ?, reservationStatus = ?, paymentMethod = ?, paymentDate = ?, paymentValue = ?, roomId = ?, ownerId = ? WHERE id = ?";

        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setString(1, reservation.getStartDate() != null ? reservation.getStartDate().format(formatter) : null);
            stmt.setString(2, reservation.getCheckInDate() != null ? reservation.getCheckInDate().format(formatter) : null);
            stmt.setString(3, reservation.getEndDate() != null ? reservation.getEndDate().format(formatter) : null);
            stmt.setString(4, reservation.getCheckOutDate() != null ? reservation.getCheckOutDate().format(formatter) : null);
            stmt.setString(5, reservation.getReservationStatus().name());
            stmt.setString(6, reservation.getPayment() != null ? reservation.getPayment().name() : null);
            stmt.setString(7, LocalDate.now().format(formatter));
            stmt.setDouble(8, reservation.calculateTotalToPay());
            stmt.setLong(9, reservation.getRoom().getId());
            stmt.setLong(10, reservation.getOwner().getId());
            stmt.setLong(11, reservation.getId());
            stmt.execute();

            updateGuests(reservation.getId(), reservation.getGuests());
            updateConsumedProducts(reservation.getId(), reservation.getConsumedProducts());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Reservation> findOneByKey(Long id) {
        String sql = "SELECT * FROM Reservation WHERE id = ?";
        Reservation reservation = null;

        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                reservation = resultSetToEntity(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(reservation);
    }

    @Override
    public boolean existsByKey(Long id) {
        String sql = "SELECT count(*) FROM Reservation WHERE id = ?";
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
    public Map<Long, Reservation> findAll() {
        String sql = "SELECT * FROM Reservation";
        Map<Long, Reservation> reservations = new HashMap<>();

        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Reservation reservation = resultSetToEntity(rs);
                reservations.put(reservation.getId(), reservation);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reservations;
    }

    @Override
    public Map<Long, Reservation> findAllByOwner(String name) {
        String sql = "SELECT r.* FROM Reservation r INNER JOIN Guest g ON r.ownerId = g.id WHERE UPPER(g.name) LIKE ?";
        Map<Long, Reservation> reservations = new HashMap<>();

        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setString(1, "%" + name.toUpperCase() + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Reservation reservation = resultSetToEntity(rs);
                reservations.put(reservation.getId(), reservation);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reservations;
    }

    @Override
    public Reservation resultSetToEntity(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong("id");
        LocalDate startDate = resultSet.getString("startDate") != null ? LocalDate.parse(resultSet.getString("startDate"), formatter) : null;
        LocalDate checkInDate = resultSet.getString("checkInDate") != null ? LocalDate.parse(resultSet.getString("checkInDate"), formatter) : null;
        LocalDate endDate = resultSet.getString("endDate") != null ? LocalDate.parse(resultSet.getString("endDate"), formatter) : null;
        LocalDate checkOutDate = resultSet.getString("checkOutDate") != null ? LocalDate.parse(resultSet.getString("checkOutDate"), formatter) : null;
        ReservationStatus reservationStatus = ReservationStatus.valueOf(resultSet.getString("reservationStatus"));
        double paymentValue = resultSet.getDouble("paymentValue");
        LocalDate paymentDate = resultSet.getString("paymentDate") != null ? LocalDate.parse(resultSet.getString("paymentDate"), formatter) : null;
        Payment paymentMethod = resultSet.getString("paymentMethod") != null ? Payment.valueOf(resultSet.getString("paymentMethod")) : null;
        long roomId = resultSet.getLong("roomId");
        long ownerId = resultSet.getLong("ownerId");

        GuestDao guestDao = new SqliteGuestDao();
        ProductDao productDao = new SqliteProductDao();
        RoomDao roomDao = new SqliteRoomDao();

        Guest owner = guestDao.findOneByKey(ownerId).orElseThrow(() -> new SQLException("Owner not found"));
        List<Guest> guests = guestDao.findAllByIdReservation(id);
        List<ConsumedProduct> consumedProducts = productDao.findAllByIdReservation(id);
        Room room = roomDao.findOneByKey(roomId).orElseThrow(() -> new SQLException("Room not found"));

        return Reservation.createCompleteReservation(id, startDate, checkInDate, endDate, checkOutDate, owner, room, reservationStatus, guests, consumedProducts, paymentMethod);
    }

    private void updateGuests(long reservationId, List<Guest> guests) throws SQLException {
        String deleteSql = "DELETE FROM GuestReservation WHERE reservationId = ?";
        try (PreparedStatement deleteStmt = ConnectionFactory.createPreparedStatement(deleteSql)) {
            deleteStmt.setLong(1, reservationId);
            deleteStmt.execute();
        }

        String insertSql = "INSERT INTO GuestReservation (guestId, reservationId) VALUES (?, ?)";
        try (PreparedStatement insertStmt = ConnectionFactory.createPreparedStatement(insertSql)) {
            for (Guest guest : guests) {
                insertStmt.setLong(1, guest.getId());
                insertStmt.setLong(2, reservationId);
                insertStmt.executeUpdate();
            }
        }
    }

    private void updateConsumedProducts(long reservationId, List<ConsumedProduct> consumedProducts) throws SQLException {
        String deleteSql = "DELETE FROM ConsumedProduct WHERE reservationId = ?";
        try (PreparedStatement deleteStmt = ConnectionFactory.createPreparedStatement(deleteSql)) {
            deleteStmt.setLong(1, reservationId);
            deleteStmt.execute();
        }

        String insertSql = "INSERT INTO ConsumedProduct (reservationId, productId, quantity, price) VALUES (?, ?, ?, ?)";
        try (PreparedStatement insertStmt = ConnectionFactory.createPreparedStatement(insertSql)) {
            for (ConsumedProduct product : consumedProducts) {
                insertStmt.setLong(1, reservationId);
                insertStmt.setLong(2, product.getProduct().getId());
                insertStmt.setInt(3, product.getQuantity());
                insertStmt.setDouble(4, product.getProduct().getPrice());
                insertStmt.executeUpdate();
            }
        }
    }

}

