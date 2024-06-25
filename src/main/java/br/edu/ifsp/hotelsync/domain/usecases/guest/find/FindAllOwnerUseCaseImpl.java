package br.edu.ifsp.hotelsync.domain.usecases.guest.find;

import br.edu.ifsp.hotelsync.domain.entities.guest.Guest;
import br.edu.ifsp.hotelsync.domain.persistence.dao.GuestDao;

import java.util.Map;

public class FindAllOwnerUseCaseImpl implements FindAllOwnerUseCase{

    private final GuestDao repository;

    public FindAllOwnerUseCaseImpl(GuestDao repository) {
        this.repository = repository;
    }

    @Override
    public Map<Long, Guest> findAll() {
        return repository.findAllOwners();
    }
}
