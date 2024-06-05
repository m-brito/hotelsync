package br.edu.ifsp.hotelsync.domain.usecases.room.update;

import br.edu.ifsp.hotelsync.domain.entities.room.Room;
import br.edu.ifsp.hotelsync.domain.persistence.dao.RoomDao;

import java.util.NoSuchElementException;
import java.util.Optional;

public class UpdateRoomUseCaseImpl implements UpdateRoomUseCase{

    private final RoomDao repository;

    public UpdateRoomUseCaseImpl(RoomDao repository) {
        this.repository = repository;
    }


    @Override
    public void updateRoom(RequestModel request) {
        Optional<Room> optionalRoom = repository.findOneByKey(request.id());
        if (optionalRoom.isPresent()) {
            Room room = optionalRoom.get();
            repository.update(room);
        } else {
            throw new NoSuchElementException("Room of id " + request.id() + " not found");
        }
    }
}
