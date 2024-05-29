package br.edu.ifsp.hotelsync.domain.usecases.reservation.find;


import br.edu.ifsp.hotelsync.domain.entities.reservation.Reservation;

public interface FindOneReservationUseCase {
    record RequestModel(Long id) {}

    Reservation findOneById(RequestModel request);
}
