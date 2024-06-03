package br.edu.ifsp.hotelsync.domain.usecases.reservation.find;

import br.edu.ifsp.hotelsync.domain.entities.reservation.Reservation;
import br.edu.ifsp.hotelsync.domain.persistence.dao.ReservationDao;

import java.util.Map;

public class FindAllReservationUseCaseImpl implements FindAllReservationUseCase {

    private final ReservationDao repository;

    public FindAllReservationUseCaseImpl(final ReservationDao repository) {
        this.repository = repository;
    }
    @Override
    public Map<Long, Reservation> findAll() {return repository.findAll();}

}
