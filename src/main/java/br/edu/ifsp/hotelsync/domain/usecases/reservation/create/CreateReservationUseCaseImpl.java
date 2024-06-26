package br.edu.ifsp.hotelsync.domain.usecases.reservation.create;

import br.edu.ifsp.hotelsync.domain.entities.reservation.Reservation;
import br.edu.ifsp.hotelsync.domain.persistence.dao.ReservationDao;
import br.edu.ifsp.hotelsync.domain.persistence.dao.RoomDao;

import java.util.NoSuchElementException;

public class CreateReservationUseCaseImpl implements CreateReservationUseCase{

    private final ReservationDao repository;
    private final RoomDao roomRepository;

    public CreateReservationUseCaseImpl(ReservationDao repository, RoomDao roomRepository){
        this.repository = repository;
        this.roomRepository = roomRepository;
    }

    @Override
    public Long createReservation(RequestModel request) {
        int roomNumber = request.room().getNumber();

        Reservation reservation = Reservation.createReservation(
                request.startDate(),
                request.endDate(),
                request.owner(),
                request.room()
            );

        if(!roomRepository.existsByNumber(roomNumber))
            throw new NoSuchElementException("Room with number " + roomNumber + " does not exist.");

        reservation.addGuest(reservation.getOwner());

        return repository.save(reservation);
    }
}
