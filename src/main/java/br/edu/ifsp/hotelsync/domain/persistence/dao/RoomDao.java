package br.edu.ifsp.hotelsync.domain.persistence.dao;

import br.edu.ifsp.hotelsync.domain.entities.room.Room;
import br.edu.ifsp.hotelsync.domain.persistence.util.Dao;

import java.time.LocalDate;
import java.util.Map;

public interface RoomDao extends Dao<Long, Room> {

    int getTotalRooms();

    Map<Long, Room> findAllAvailable(LocalDate startDate, LocalDate endDate);

}
