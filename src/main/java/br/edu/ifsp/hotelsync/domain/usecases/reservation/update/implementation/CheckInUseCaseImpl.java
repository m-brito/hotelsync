package br.edu.ifsp.hotelsync.domain.usecases.reservation.update.implementation;

import br.edu.ifsp.hotelsync.domain.entities.reservation.Reservation;
import br.edu.ifsp.hotelsync.domain.persistence.dao.ReservationDao;
import br.edu.ifsp.hotelsync.domain.usecases.reservation.update.interfaces.CheckInUseCase;

import java.util.NoSuchElementException;

public class CheckInUseCaseImpl implements CheckInUseCase {

    private final ReservationDao repository;

    public CheckInUseCaseImpl(ReservationDao repository) {this.repository = repository;}

    @Override
    public void doCheckIn(RequestModel request) {
        Long id = request.id();

        Reservation reservation = repository.findOneByKey(id).orElseThrow(
                () -> new NoSuchElementException("Reservation of id " + id + " not found")
        );
        reservation.checkIn();
        repository.checkIn(id);

    }
}
