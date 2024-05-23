package br.edu.ifsp.hotelsync.domain.usecases.guest.find;

import br.edu.ifsp.hotelsync.domain.entities.guest.Guest;
import br.edu.ifsp.hotelsync.domain.persistence.dao.GuestDAO;

public class FindOneGuestUseCaseImpl implements FindOneGuestUseCase{
    private final GuestDAO repository;

    public FindOneGuestUseCaseImpl(GuestDAO repository) {
        this.repository = repository;
    }

    @Override
    public Guest findOneById(RequestModel request) {
        return repository.findOneByKey(request.id()).orElseThrow();
    }
}
