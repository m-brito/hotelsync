package br.edu.ifsp.hotelsync.domain.usecases.reservation.update;

import br.edu.ifsp.hotelsync.domain.entities.reservation.Reservation;
import br.edu.ifsp.hotelsync.domain.persistence.dao.ReservationDAO;

import java.util.NoSuchElementException;

public class CheckInUseCaseImpl implements CheckInUseCase{

    private final ReservationDAO repository;

    public CheckInUseCaseImpl(ReservationDAO repository) {this.repository = repository;}

    @Override
    public void doCheckIn(RequestModel request) {
        Long id = request.id();

        Reservation reservation = repository.findOneByKey(id).orElseThrow(
                () -> new NoSuchElementException("Product of id " + id + " not found")
        );
        reservation.checkIn();
        repository.checkIn(id);

    }
}
