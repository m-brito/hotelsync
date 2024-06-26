package br.edu.ifsp.hotelsync.domain.usecases.reservation.find;

import br.edu.ifsp.hotelsync.domain.entities.reservation.Reservation;

import java.util.Map;

public interface FindAllReservationByOwnerUseCase {
    record RequestModel(String name) {}

    Map<Long, Reservation> findAllByOwner(RequestModel requestModel);
}
