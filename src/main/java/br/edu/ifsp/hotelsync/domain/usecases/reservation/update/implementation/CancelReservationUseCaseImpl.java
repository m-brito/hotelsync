package br.edu.ifsp.hotelsync.domain.usecases.reservation.update.implementation;

import br.edu.ifsp.hotelsync.domain.entities.reservation.Reservation;
import br.edu.ifsp.hotelsync.domain.persistence.dao.ReservationDAO;
import br.edu.ifsp.hotelsync.domain.usecases.reservation.update.interfaces.CancelReservationUseCase;
import br.edu.ifsp.hotelsync.domain.usecases.reservation.update.interfaces.CheckInUseCase;

import java.util.NoSuchElementException;

public class CancelReservationUseCaseImpl implements CancelReservationUseCase {

    private final ReservationDAO repository;

    public CancelReservationUseCaseImpl(ReservationDAO repository) {this.repository = repository;}

    @Override
    public void cancelReservation(CheckInUseCase.RequestModel request) {
        Long id = request.id();

        Reservation reservation = repository.findOneByKey(id).orElseThrow(
                () -> new NoSuchElementException("Reservation of id " + id + " not found")
        );
        reservation.cancelReservation();
        repository.cancelReservation(id);
    }
}
