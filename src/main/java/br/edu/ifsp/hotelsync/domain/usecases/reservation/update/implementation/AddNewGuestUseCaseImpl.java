package br.edu.ifsp.hotelsync.domain.usecases.reservation.update.implementation;

import br.edu.ifsp.hotelsync.domain.entities.guest.Guest;
import br.edu.ifsp.hotelsync.domain.entities.reservation.Reservation;
import br.edu.ifsp.hotelsync.domain.persistence.dao.GuestDAO;
import br.edu.ifsp.hotelsync.domain.persistence.dao.ReservationDAO;
import br.edu.ifsp.hotelsync.domain.usecases.reservation.update.interfaces.AddNewGuestUseCase;

import java.util.NoSuchElementException;
import java.util.Optional;

public class AddNewGuestUseCaseImpl implements AddNewGuestUseCase {

    private final GuestDAO  guestRepository;
    private final ReservationDAO reservationRepository;

    public AddNewGuestUseCaseImpl(GuestDAO guestRepository, ReservationDAO reservationRepository) {
        this.guestRepository = guestRepository;
        this.reservationRepository = reservationRepository;
    }

    @Override
    public void addNewGuest(RequestModel request) {
        Optional<Guest> guest = guestRepository.findOneByKey(request.idGuest());
        if(guest.isEmpty())
            throw new NoSuchElementException("Guest of id " + request.idReservation() + " not found");

        Optional<Reservation> reservation = reservationRepository.findOneByKey(request.idReservation());
        if(reservation.isEmpty())
            throw new NoSuchElementException("Reservation of id " + request.idReservation() + " not found");

        if(reservation.get().getGuests().contains(guest.get()))
            throw new NoSuchElementException("Guest " + request.idReservation() + " Already exists in this reservation");

        reservation.get().addGuest(guest.get());
    }
}
