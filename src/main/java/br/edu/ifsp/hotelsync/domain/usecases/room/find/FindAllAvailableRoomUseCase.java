package br.edu.ifsp.hotelsync.domain.usecases.room.find;

import br.edu.ifsp.hotelsync.domain.entities.room.Room;

import java.util.Map;

public interface FindAllAvailableRoomUseCase {
    Map<Long, Room> findAllAvailable();
}
