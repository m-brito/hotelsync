package br.edu.ifsp.hotelsync.domain.usecases.reservation.update.implementation;

import br.edu.ifsp.hotelsync.domain.entities.reservation.Reservation;
import br.edu.ifsp.hotelsync.domain.persistence.dao.ReservationDao;
import br.edu.ifsp.hotelsync.domain.usecases.reservation.update.interfaces.CheckOutUseCase;

import java.util.NoSuchElementException;

public class CheckOutUseCaseImpl implements CheckOutUseCase {

    private final ReservationDao repository;

    public CheckOutUseCaseImpl(ReservationDao repository) {this.repository = repository;}

    @Override
    public void doCheckOut(RequestModel request) {
        Long id = request.id();

        Reservation reservation = repository.findOneByKey(id).orElseThrow(
                () -> new NoSuchElementException("Reservation of id " + id + " not found")
        );
        String paymentMethod = reservation.getPayment().getMethod();
        reservation.checkOut(paymentMethod);
        repository.update(reservation);
    }
}
