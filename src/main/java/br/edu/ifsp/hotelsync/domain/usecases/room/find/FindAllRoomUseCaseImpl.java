package br.edu.ifsp.hotelsync.domain.usecases.room.find;

import br.edu.ifsp.hotelsync.domain.entities.room.Room;
import br.edu.ifsp.hotelsync.domain.persistence.dao.RoomDao;

import java.util.Map;

public class FindAllRoomUseCaseImpl implements FindAllRoomUseCase {

    private final RoomDao repository;

    public FindAllRoomUseCaseImpl(RoomDao repository) {
        this.repository = repository;
    }

    @Override
    public Map<Long, Room> findAll() {
        return repository.findAll();
    }
}