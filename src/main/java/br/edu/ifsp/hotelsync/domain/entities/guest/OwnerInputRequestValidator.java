package br.edu.ifsp.hotelsync.domain.entities.guest;

import br.edu.ifsp.hotelsync.domain.usecases.utils.Notification;
import br.edu.ifsp.hotelsync.domain.usecases.utils.Validator;

import java.time.LocalDate;
import java.time.Period;

public class OwnerInputRequestValidator extends Validator<Guest> {
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
        if(Validator.isNullOrEmpty(guest.getPronouns()))
            notification.addError("Pronouns is null or empty");
        if(guest.getBirthdate() == null)
            notification.addError("Birthdate is null");
        if(Period.between(guest.getBirthdate(), LocalDate.now()).getYears() < 18)
            notification.addError(" Reservation Owners must not be under the age of 18");
        if(Period.between(guest.getBirthdate(), LocalDate.now()).getYears() > 140)
            notification.addError(" Reservation Owner age can't be higher than human lifespan");
        if(guest.getCpf() == null)
            notification.addError(" Reservation Owners must register CPF ");
        if(guest.getAddress() == null)
            notification.addError(" Reservation Owners must register address");
        if(guest.getPhone() == null)
            notification.addError(" Reservation Owners must register phone");
        return notification;
    }
}
