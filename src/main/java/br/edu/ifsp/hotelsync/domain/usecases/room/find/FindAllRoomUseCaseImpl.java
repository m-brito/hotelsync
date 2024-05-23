package br.edu.ifsp.hotelsync.domain.usecases.room.find;

import br.edu.ifsp.hotelsync.domain.entities.room.Room;
import br.edu.ifsp.hotelsync.domain.persistence.dao.RoomDAO;

import java.util.Map;

public class FindAllRoomUseCaseImpl implements FindAllRoomUseCase {

    private final RoomDAO repository;

    public FindAllRoomUseCaseImpl(RoomDAO repository) {
        this.repository = repository;
    }

    @Override
    public Map<Long, Room> findAll() {
        return repository.findAll();
    }
}