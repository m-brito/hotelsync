package br.edu.ifsp.hotelsync.domain.usecases.reservation.update.implementation;

import br.edu.ifsp.hotelsync.domain.entities.guest.Guest;
import br.edu.ifsp.hotelsync.domain.entities.reservation.Reservation;
import br.edu.ifsp.hotelsync.domain.persistence.dao.GuestDao;
import br.edu.ifsp.hotelsync.domain.persistence.dao.ReservationDao;
import br.edu.ifsp.hotelsync.domain.usecases.reservation.update.interfaces.AddNewGuestUseCase;

import java.util.NoSuchElementException;
import java.util.Optional;

public class AddNewGuestUseCaseImpl implements AddNewGuestUseCase {

    private final GuestDao guestRepository;
    private final ReservationDao reservationRepository;

    public AddNewGuestUseCaseImpl(GuestDao guestRepository, ReservationDao reservationRepository) {
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
