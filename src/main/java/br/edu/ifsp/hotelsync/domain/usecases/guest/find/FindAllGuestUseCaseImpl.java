package br.edu.ifsp.hotelsync.domain.usecases.guest.find;

import br.edu.ifsp.hotelsync.domain.entities.guest.Guest;
import br.edu.ifsp.hotelsync.domain.persistence.dao.GuestDao;

import java.util.Map;

public class FindAllGuestUseCaseImpl implements FindAllGuestUseCase{

    private final GuestDao repository;

    public FindAllGuestUseCaseImpl(GuestDao repository) {
        this.repository = repository;
    }

    @Override
    public Map<Long, Guest> findAll() {
        return repository.findAll();
    }
}
