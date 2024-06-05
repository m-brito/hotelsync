package br.edu.ifsp.hotelsync.domain.entities.guest;

import br.edu.ifsp.hotelsync.domain.usecases.utils.Notification;
import br.edu.ifsp.hotelsync.domain.usecases.utils.Validator;

import java.time.LocalDate;
import java.time.Period;

public class GuestInputRequestValidator extends Validator<Guest> {
    @Override
    public Notification validate(Guest guest) {

        Notification notification = new Notification();

        if(guest == null) {
            notification.addError("Guest is null");
            return notification;
        }
        if(Validator.isNullOrEmpty(guest.getName()))
            notification.addError("Name is null or empty");
        if(guest.getName().length() > 20)
            notification.addError("Name may not exceed 20 characters in size");

        if(guest.getBirthdate() == null)
            notification.addError("Birthdate is null");
        if(Period.between(guest.getBirthdate(), LocalDate.now()).getYears() < 0)
            notification.addError(" Guest age must be greater than or equal to 0");
        if(Period.between(guest.getBirthdate(), LocalDate.now()).getYears() > 140)
            notification.addError(" Reservation Owner age can't be higher than human lifespan");

        if(guest.getCpf() == null)
            notification.addError(" Reservation Owners must register CPF ");

        return notification;
    }
}
