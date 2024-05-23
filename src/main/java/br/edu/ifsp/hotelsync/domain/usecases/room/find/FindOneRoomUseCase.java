package br.edu.ifsp.hotelsync.domain.usecases.room.find;

import br.edu.ifsp.hotelsync.domain.entities.room.Room;

public interface FindOneRoomUseCase {
    Room findRoom(Long id);
}
