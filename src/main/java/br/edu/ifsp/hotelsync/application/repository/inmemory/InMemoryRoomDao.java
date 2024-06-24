package br.edu.ifsp.hotelsync.application.repository.inmemory;

import br.edu.ifsp.hotelsync.domain.entities.room.Room;
import br.edu.ifsp.hotelsync.domain.entities.room.RoomStatus;
import br.edu.ifsp.hotelsync.domain.persistence.dao.RoomDao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class InMemoryRoomDao implements RoomDao {
    private static final Map<Long, Room> rooms = new HashMap<>();
    private static long id = 0;

    @Override
    public Long save(Room room) {
        rooms.put(++id, room);
        return id;
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
    public boolean existsByKey(Long id) {
        return rooms.containsKey(id);
    }

    @Override
    public Map<Long, Room> findAll() {
        return Map.copyOf(rooms);
    }

    @Override
    public Map<Long, Room> findAllAvailable(LocalDate startDate, LocalDate endDate) {
        return rooms.values().stream()
                .filter(r -> r.getRoomStatus() == RoomStatus.AVAILABLE)
                .collect(Collectors.toMap(Room::getId, Function.identity()));
    }


    @Override
    public void deleteByKey(Long id) {
        rooms.remove(id);
    }

    @Override
    public Room resultSetToEntity(ResultSet resultSet) throws SQLException {
        return null;
    }

    @Override
    public int getTotalRooms() {
        return rooms.size();
    }
}
