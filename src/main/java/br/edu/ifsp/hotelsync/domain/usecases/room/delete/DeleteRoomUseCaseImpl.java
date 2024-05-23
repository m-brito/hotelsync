package br.edu.ifsp.hotelsync.domain.usecases.room.delete;

import br.edu.ifsp.hotelsync.domain.persistence.dao.RoomDAO;

import java.util.NoSuchElementException;

public class DeleteRoomUseCaseImpl implements DeleteRoomUseCase{

    private final RoomDAO repository;

    public DeleteRoomUseCaseImpl(RoomDAO repository) {
        this.repository = repository;
    }

    @Override
    public void deleteRoom(Long id) {
        if (!repository.existsByKey(id))
            throw new NoSuchElementException("Room of id " + id + " not found");

        repository.deleteByKey(id);
    }
}
