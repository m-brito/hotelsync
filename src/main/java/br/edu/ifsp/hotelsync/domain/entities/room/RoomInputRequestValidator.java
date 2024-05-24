package br.edu.ifsp.hotelsync.domain.entities.room;

import br.edu.ifsp.hotelsync.domain.usecases.utils.Notification;
import br.edu.ifsp.hotelsync.domain.usecases.utils.Validator;

public class RoomInputRequestValidator extends Validator<Room> {
    @Override
    public Notification validate(Room room) {
        Notification notification = new Notification();
        if (room == null) {
            notification.addError("Room is null");
            return notification;
        }
        if (room.getNumber() <= 0) {
            notification.addError("Room number must be greater than zero");
        }
        if (room.getNumberOfBeds() <= 0) {
            notification.addError("Number of beds must be greater than zero");
        }
        if (Validator.isNullOrEmpty(room.getTypeOfBed())) {
            notification.addError("Type of bed is null or empty");
        }
        if (room.getArea() <= 0) {
            notification.addError("Room area must be greater than zero");
        }
        if (Validator.isNullOrEmpty(room.getDescription())) {
            notification.addError("Description is null or empty");
        }
        if (room.getRoomCategory() == null) {
            notification.addError("Room category is null");
        }
        if (room.getRoomStatus() == null) {
            notification.addError("Room status is null");
        }

        return notification;
    }
}
