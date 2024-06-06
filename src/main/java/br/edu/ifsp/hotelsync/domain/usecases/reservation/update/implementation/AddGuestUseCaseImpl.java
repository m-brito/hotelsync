package br.edu.ifsp.hotelsync.domain.usecases.reservation.update.implementation;

import br.edu.ifsp.hotelsync.domain.entities.guest.Guest;
import br.edu.ifsp.hotelsync.domain.entities.reservation.Reservation;
import br.edu.ifsp.hotelsync.domain.persistence.dao.GuestDao;
import br.edu.ifsp.hotelsync.domain.persistence.dao.ReservationDao;
import br.edu.ifsp.hotelsync.domain.usecases.reservation.update.interfaces.AddGuestUseCase;

import java.util.NoSuchElementException;
import java.util.Optional;

public class AddGuestUseCaseImpl implements AddGuestUseCase {

    private final GuestDao guestRepository;
    private final ReservationDao reservationRepository;

    public AddGuestUseCaseImpl(GuestDao guestRepository, ReservationDao reservationRepository) {
        this.guestRepository = guestRepository;
        this.reservationRepository = reservationRepository;
    }

    @Override
    public void addGuest(RequestModel request) {
        Optional<Guest> guest = guestRepository.findOneByKey(request.idGuest());
        if(guest.isEmpty())
            throw new NoSuchElementException("Guest of id " + request.idReservation() + " not found");

        Optional<Reservation> reservation = reservationRepository.findOneByKey(request.idReservation());
        if(reservation.isEmpty())
            throw new NoSuchElementException("Reservation of id " + request.idReservation() + " not found");

        if(reservation.get().getGuests().contains(guest.get()))
            throw new NoSuchElementException("Guest " + request.idReservation() + " Already exists in this reservation");

        reservation.get().addGuest(guest.get());
        reservationRepository.update(reservation.get());
    }
}
