package br.edu.ifsp.hotelsync.domain.usecases.guest.find;

import br.edu.ifsp.hotelsync.domain.entities.guest.Guest;
import br.edu.ifsp.hotelsync.domain.persistence.dao.GuestDao;

import java.util.Map;

public class FindAllOwnerByNameUseCaseImpl implements FindAllOwnerByNameUseCase{

    private final GuestDao repository;

    public FindAllOwnerByNameUseCaseImpl(GuestDao repository) {
        this.repository = repository;
    }

    @Override
    public Map<Long, Guest> findAllByName(RequestModel requestModel) {
        return repository.findAllOwnersByName(requestModel.name());
    }
}
