package br.edu.ifsp.hotelsync.domain.usecases.reservation.find;

import br.edu.ifsp.hotelsync.domain.entities.reservation.Reservation;
import br.edu.ifsp.hotelsync.domain.persistence.dao.ReservationDao;

import java.util.Map;

public class FindAllReservationByOwnerUseCaseImpl implements FindAllReservationByOwnerUseCase {

    private final ReservationDao repository;

    public FindAllReservationByOwnerUseCaseImpl(final ReservationDao repository) {
        this.repository = repository;
    }
    @Override
    public Map<Long, Reservation> findAllByOwner(RequestModel requestModel) {
        return repository.findAllByOwner(requestModel.name());
    }

}
