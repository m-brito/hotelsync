package br.edu.ifsp.hotelsync.domain.usecases.reservation.find;



import br.edu.ifsp.hotelsync.domain.entities.reservation.Reservation;

import java.util.Map;

public interface FindAllReservationUseCase {
    Map<Long, Reservation> findAll();
}
