package br.edu.ifsp.hotelsync.domain.usecases.reservation.update.implementation;

import br.edu.ifsp.hotelsync.domain.entities.reservation.Reservation;
import br.edu.ifsp.hotelsync.domain.entities.room.Room;
import br.edu.ifsp.hotelsync.domain.persistence.dao.ReservationDao;
import br.edu.ifsp.hotelsync.domain.persistence.dao.RoomDao;
import br.edu.ifsp.hotelsync.domain.usecases.reservation.update.interfaces.CheckInUseCase;

import java.util.NoSuchElementException;

public class CheckInUseCaseImpl implements CheckInUseCase {

    private final ReservationDao reservationRepository;
    private final RoomDao roomRepository;

    public CheckInUseCaseImpl(ReservationDao reservationRepository, RoomDao roomRepository) {
        this.reservationRepository = reservationRepository;
        this.roomRepository = roomRepository;
    }

    @Override
    public void doCheckIn(RequestModel request) {
        Long id = request.id();

        Reservation reservation = reservationRepository.findOneByKey(id).orElseThrow(
                () -> new NoSuchElementException("Reservation of id " + id + " not found")
        );
        reservation.checkIn();
        Room room = reservation.getRoom();
        roomRepository.update(room);
        reservationRepository.update(reservation);

    }
}
