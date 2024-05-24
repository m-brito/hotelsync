package br.edu.ifsp.hotelsync.domain.entities.reservation;

import br.edu.ifsp.hotelsync.domain.usecases.utils.Notification;
import br.edu.ifsp.hotelsync.domain.usecases.utils.Validator;

public class ReservationInputRequestValidator extends Validator<Reservation> {

    @Override
    public Notification validate(Reservation reservation) {
        Notification notification = new Notification();
        if (reservation == null) {
            notification.addError("Reservation is null");
            return notification;
        }
        if (reservation.getCheckInDate() == null) {
            notification.addError("Check-in date is null");
        }
        if (reservation.getCheckOutDate() == null) {
            notification.addError("Check-out date is null");
        }
        if (reservation.getOwner() == null) {
            notification.addError("Owner is null");
        }
        if (reservation.getRoom() == null) {
            notification.addError("Room is null");
        }
        if (reservation.getReservationStatus() == null) {
            notification.addError("Reservation status is null");
        }
        if (reservation.getPayment() == null) {
            notification.addError("Payment is null");
        }

        return notification;
    }
}
