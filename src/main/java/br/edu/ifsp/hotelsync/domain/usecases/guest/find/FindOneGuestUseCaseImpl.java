package br.edu.ifsp.hotelsync.domain.usecases.guest.find;

import br.edu.ifsp.hotelsync.domain.entities.guest.Guest;
import br.edu.ifsp.hotelsync.domain.persistence.dao.GuestDao;

import java.util.NoSuchElementException;

public class FindOneGuestUseCaseImpl implements FindOneGuestUseCase{
    private final GuestDao repository;

    public FindOneGuestUseCaseImpl(GuestDao repository) {
        this.repository = repository;
    }

    @Override
    public Guest findOneById(RequestModel request) {
        return repository.findOneByKey(request.id()).orElseThrow(
                () -> new NoSuchElementException("Guest of id " + request.id() + " not found")
        );
    }
}
