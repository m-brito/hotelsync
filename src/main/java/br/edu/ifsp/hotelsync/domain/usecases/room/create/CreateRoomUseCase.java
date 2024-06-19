package br.edu.ifsp.hotelsync.domain.usecases.room.create;

import br.edu.ifsp.hotelsync.domain.entities.room.RoomCategory;
import br.edu.ifsp.hotelsync.domain.entities.room.RoomStatus;

public interface CreateRoomUseCase {
    record RequestModel(int number,
                        int numberOfBeds,
                        String typeOfBed,
                        RoomCategory roomCategory,
                        RoomStatus roomStatus,
                        String description,
                        double area) {}

    Long createRoom(RequestModel requestModel);
}
