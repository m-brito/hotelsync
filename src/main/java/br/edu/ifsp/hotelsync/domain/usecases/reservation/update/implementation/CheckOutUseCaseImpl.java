package br.edu.ifsp.hotelsync.domain.usecases.reservation.update.implementation;

import br.edu.ifsp.hotelsync.domain.entities.reservation.Payment;
import br.edu.ifsp.hotelsync.domain.entities.reservation.Reservation;
import br.edu.ifsp.hotelsync.domain.entities.room.Room;
import br.edu.ifsp.hotelsync.domain.persistence.dao.ReservationDao;
import br.edu.ifsp.hotelsync.domain.persistence.dao.RoomDao;
import br.edu.ifsp.hotelsync.domain.usecases.reservation.update.interfaces.CheckOutUseCase;

import java.util.NoSuchElementException;

public class CheckOutUseCaseImpl implements CheckOutUseCase {

    private final ReservationDao repository;
    private final RoomDao roomRepository;


    public CheckOutUseCaseImpl(ReservationDao reservationRepository, RoomDao roomRepository){
        this.repository = reservationRepository;
        this.roomRepository = roomRepository
        ;}

    @Override
    public void doCheckOut(RequestModel request) {
        Long id = request.id();

        Reservation reservation = repository.findOneByKey(id).orElseThrow(
                () -> new NoSuchElementException("Reservation of id " + id + " not found")
        );

        Payment paymentMethod = reservation.getPayment();
        reservation.checkOut(paymentMethod.name());
        Room room = reservation.getRoom();
        roomRepository.update(room);
        repository.update(reservation);
    }
}