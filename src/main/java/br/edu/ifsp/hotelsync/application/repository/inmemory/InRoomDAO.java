package br.edu.ifsp.hotelsync.application.repository.inmemory;

import br.edu.ifsp.hotelsync.domain.entities.room.Room;
import br.edu.ifsp.hotelsync.domain.persistence.dao.RoomDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class InRoomDAO implements RoomDAO {
    private static final Map<Long, Room> rooms = new HashMap<>();
    private static long id = 0;

    @Override
    public void save(Room room) {
        rooms.put(++id, room);
    }

    @Override
    public void update(Room room) {
        rooms.put(room.getId(), room);
    }

    @Override
    public Optional<Room> findOneByKey(Long id) {
        return Optional.ofNullable(rooms.get(id));
    }

    @Override
    public Map<Long, Room> findAll() {
        return Map.copyOf(rooms);
    }

    @Override
    public void deleteByKey(Long id) {
        rooms.remove(id);
    }

    @Override
    public Room resultSetToEntity(ResultSet resultSet) throws SQLException {
        return null;
    }
}
