package br.edu.ifsp.hotelsync.domain.usecases.room.update;

import br.edu.ifsp.hotelsync.domain.entities.room.Room;
import br.edu.ifsp.hotelsync.domain.persistence.dao.RoomDAO;

import java.util.NoSuchElementException;

public class UpdateRoomUseCaseImpl implements UpdateRoomUseCase{

    private final RoomDAO repository;

    public UpdateRoomUseCaseImpl(RoomDAO repository) {
        this.repository = repository;
    }

    @Override
    public void updateRoom(RequestModel requestModel) {
        if (!repository.existsByKey(requestModel.id()))
            throw new NoSuchElementException("Room of id " + requestModel.id() + " not found");

        Room room = new Room(requestModel.id(), requestModel.number(), requestModel.numberOfBeds(), requestModel.typeOfBed(), requestModel.roomCategory(), requestModel.description(), requestModel.roomStatus(), requestModel.area());
        repository.update(room);
    }
}
