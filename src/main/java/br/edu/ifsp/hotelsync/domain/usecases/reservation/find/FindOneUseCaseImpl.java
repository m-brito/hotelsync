package br.edu.ifsp.hotelsync.domain.usecases.reservation.find;

import br.edu.ifsp.hotelsync.domain.entities.reservation.Reservation;

import br.edu.ifsp.hotelsync.domain.persistence.dao.ReservationDao;

import java.util.NoSuchElementException;

public class FindOneUseCaseImpl implements FindOneReservationUseCase{

    private final ReservationDao repository;

    public FindOneUseCaseImpl(ReservationDao repository) {
        this.repository = repository;
    }

    public Reservation findOneById(RequestModel request) {
        return repository.findOneByKey(request.id()).
                orElseThrow(() -> new NoSuchElementException("Guest of id " + request.id() + " not found")
        );
    }
}
