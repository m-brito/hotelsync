package br.edu.ifsp.hotelsync.application.repository.sqlite.dao;

import br.edu.ifsp.hotelsync.application.repository.sqlite.ConnectionFactory;
import br.edu.ifsp.hotelsync.domain.entities.room.Room;
import br.edu.ifsp.hotelsync.domain.entities.room.RoomCategory;
import br.edu.ifsp.hotelsync.domain.entities.room.RoomStatus;
import br.edu.ifsp.hotelsync.domain.persistence.dao.RoomDao;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class SqliteRoomDao implements RoomDao {

    @Override
    public Long save(Room room) {
        String sql = "INSERT INTO Room(number, numberOfBeds, typeOfBed, roomCategory, description, roomStatus, area) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setInt(1, room.getNumber());
            stmt.setInt(2, room.getNumberOfBeds());
            stmt.setString(3, room.getTypeOfBed());
            stmt.setString(4, room.getRoomCategory().toString());
            stmt.setString(5, room.getDescription());
            stmt.setString(6, room.getRoomStatus().toString());
            stmt.setDouble(7, room.getArea());
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
    public void update(Room room) {
        String sql = "UPDATE Room SET number = ?, numberOfBeds = ?, typeOfBed = ?, roomCategory = ?, description = ?, roomStatus = ?, area = ? WHERE id = ?";
        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setInt(1, room.getNumber());
            stmt.setInt(2, room.getNumberOfBeds());
            stmt.setString(3, room.getTypeOfBed());
            stmt.setString(4, room.getRoomCategory().toString());
            stmt.setString(5, room.getDescription());
            stmt.setString(6, room.getRoomStatus().toString());
            stmt.setDouble(7, room.getArea());
            stmt.setLong(8, room.getId());
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Room> findOneByKey(Long id) {
        String sql = "SELECT * FROM Room WHERE id = ?";
        Room room = null;
        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                room = resultSetToEntity(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(room);
    }

    @Override
    public boolean existsByKey(Long id) {
        String sql = "SELECT count(*) FROM Room WHERE id = ?";
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
    public Map<Long, Room> findAll() {
        String sql = "SELECT * FROM Room";
        Map<Long, Room> rooms = new HashMap<>();
        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Room room = resultSetToEntity(rs);
                rooms.put(room.getId(), room);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rooms;
    }

    @Override
    public Map<Long, Room> findAllAvailable() {
        String sql = "SELECT * FROM Room WHERE roomStatus = 'AVAILABLE'";
        Map<Long, Room> rooms = new HashMap<>();
        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Room room = resultSetToEntity(rs);
                rooms.put(room.getId(), room);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rooms;
    }

    @Override
    public void deleteByKey(Long id) {
        String sql = "DELETE FROM Room WHERE id = ?";
        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setLong(1, id);
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Room resultSetToEntity(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong("id");
        int number = resultSet.getInt("number");
        int numberOfBeds = resultSet.getInt("numberOfBeds");
        String typeOfBed = resultSet.getString("typeOfBed");
        RoomCategory roomCategory = RoomCategory.valueOf(resultSet.getString("roomCategory"));
        String description = resultSet.getString("description");
        RoomStatus roomStatus = RoomStatus.valueOf(resultSet.getString("roomStatus"));
        double area = resultSet.getDouble("area");

        return Room.createRoomWithId(id, number, numberOfBeds, typeOfBed, roomCategory, description, roomStatus, area);
    }

    @Override
    public int getTotalRooms() {
        String sql = "SELECT COUNT(*) AS total FROM Room";
        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("total");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}


