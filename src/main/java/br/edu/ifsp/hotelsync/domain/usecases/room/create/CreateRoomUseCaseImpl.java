package br.edu.ifsp.hotelsync.domain.usecases.room.create;

import br.edu.ifsp.hotelsync.domain.entities.room.Room;
import br.edu.ifsp.hotelsync.domain.persistence.dao.RoomDAO;

public class CreateRoomUseCaseImpl implements CreateRoomUseCase {

    private final RoomDAO repository;

    public CreateRoomUseCaseImpl(RoomDAO repository) {
        this.repository = repository;
    }

    @Override
    public void createRoom(RequestModel requestModel) {
        Room room = new Room(requestModel.number(), requestModel.numberOfBeds(), requestModel.typeOfBed(),
                requestModel.roomCategory(), requestModel.description(),
                requestModel.roomStatus(), requestModel.area());
        repository.save(room);
    }
}
