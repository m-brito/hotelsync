package br.edu.ifsp.hotelsync.domain.usecases.reservation.create;

import br.edu.ifsp.hotelsync.domain.entities.guest.Guest;
import br.edu.ifsp.hotelsync.domain.entities.reservation.ConsumedProduct;
import br.edu.ifsp.hotelsync.domain.entities.reservation.Payment;
import br.edu.ifsp.hotelsync.domain.entities.reservation.ReservationStatus;
import br.edu.ifsp.hotelsync.domain.entities.room.Room;

import java.time.LocalDate;
import java.util.List;

public interface CreateReservationUseCase {
    record RequestModel(
            LocalDate startDate,
            LocalDate checkInDate,
            LocalDate endDate,
            LocalDate checkOutDate,
            Guest owner,
            Room room,
            ReservationStatus reservationStatus,
            List<Guest> guests,
            List<ConsumedProduct> consumedProducts,
            Payment payment
            ) {}
    void createReservation(CreateReservationUseCase.RequestModel request);
}
