package br.edu.ifsp.hotelsync.domain.usecases.room.update;

import br.edu.ifsp.hotelsync.domain.entities.room.RoomCategory;
import br.edu.ifsp.hotelsync.domain.entities.room.RoomStatus;

public interface UpdateRoomUseCase {
    record RequestModel(Long id,
                        int number,
                        int numberOfBeds,
                        String typeOfBed,
                        RoomCategory roomCategory,
                        String description,
                        RoomStatus roomStatus,
                        double area) {}

    void updateRoom(RequestModel requestModel);
}
