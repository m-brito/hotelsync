package br.edu.ifsp.hotelsync.domain.persistence.dao;

import br.edu.ifsp.hotelsync.domain.entities.reservation.Reservation;
import br.edu.ifsp.hotelsync.domain.persistence.util.Dao;

public interface ReservationDao extends Dao<Long, Reservation> {
    boolean cancelReservation(Long reservationId);

    boolean checkIn(Long reservationId);

    boolean checkOut(Long reservationId, String paymentMethod);
}
