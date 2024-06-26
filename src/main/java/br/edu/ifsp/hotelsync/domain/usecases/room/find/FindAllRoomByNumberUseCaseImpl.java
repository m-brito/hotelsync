package br.edu.ifsp.hotelsync.domain.usecases.room.find;

import br.edu.ifsp.hotelsync.domain.entities.room.Room;
import br.edu.ifsp.hotelsync.domain.persistence.dao.RoomDao;

import java.util.Map;

public class FindAllRoomByNumberUseCaseImpl implements FindAllRoomByNumberUseCase {

    private final RoomDao repository;

    public FindAllRoomByNumberUseCaseImpl(RoomDao repository) {
        this.repository = repository;
    }

    @Override
    public Map<Long, Room> findAllByNumber(RequestModel requestModel) {
        return repository.findAllByNumber(requestModel.number());
    }
}