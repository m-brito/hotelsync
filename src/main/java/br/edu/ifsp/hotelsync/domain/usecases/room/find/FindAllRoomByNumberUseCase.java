package br.edu.ifsp.hotelsync.domain.usecases.room.find;

import br.edu.ifsp.hotelsync.domain.entities.room.Room;

import java.util.Map;

public interface FindAllRoomByNumberUseCase {
    record RequestModel(String number) {}

    Map<Long, Room> findAllByNumber(RequestModel requestModel);
}
