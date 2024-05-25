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
        if (reservation.getStartDate() == null) {
            notification.addError("Start date is null");
        }
        if (reservation.getEndDate() == null) {
            notification.addError("End date is null");
        }
        if (reservation.getOwner() == null) {
            notification.addError("Owner is null");
        }
        if (reservation.getRoom() == null) {
            notification.addError("Room is null");
        }


        return notification;
    }
}
