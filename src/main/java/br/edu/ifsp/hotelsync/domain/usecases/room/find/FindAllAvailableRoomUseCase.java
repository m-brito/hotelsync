package br.edu.ifsp.hotelsync.domain.usecases.room.find;

import br.edu.ifsp.hotelsync.domain.entities.room.Room;

import java.time.LocalDate;
import java.util.Map;

public interface FindAllAvailableRoomUseCase {
    record RequestModel(LocalDate initialDate, LocalDate finalDate){}

    Map<Long, Room> findAllAvailable(RequestModel request);
}
