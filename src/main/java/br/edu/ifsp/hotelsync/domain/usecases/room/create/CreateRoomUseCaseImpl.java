package br.edu.ifsp.hotelsync.domain.usecases.room.create;

import br.edu.ifsp.hotelsync.domain.entities.room.Room;
import br.edu.ifsp.hotelsync.domain.persistence.dao.RoomDao;

public class CreateRoomUseCaseImpl implements CreateRoomUseCase {

    private final RoomDao repository;

    public CreateRoomUseCaseImpl(RoomDao repository) {
        this.repository = repository;
    }

    @Override
    public void createRoom(RequestModel requestModel) {
        Room room = Room.createRoom(requestModel.number(), requestModel.numberOfBeds(), requestModel.typeOfBed(),
                requestModel.roomCategory(), requestModel.description(),
                requestModel.roomStatus(), requestModel.area());
        repository.save(room);
    }
}
