package br.edu.ifsp.hotelsync.domain.usecases.guest.find;

import br.edu.ifsp.hotelsync.domain.entities.guest.Guest;

import java.util.Map;

public interface FindAllOwnerUseCase {
    Map<Long, Guest> findAll();

}
