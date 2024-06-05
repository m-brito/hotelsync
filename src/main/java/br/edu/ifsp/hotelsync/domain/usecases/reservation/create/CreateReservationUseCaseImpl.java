package br.edu.ifsp.hotelsync.domain.usecases.reservation.create;

import br.edu.ifsp.hotelsync.domain.entities.reservation.Reservation;
import br.edu.ifsp.hotelsync.domain.persistence.dao.ReservationDao;

public class CreateReservationUseCaseImpl implements CreateReservationUseCase{

    private final ReservationDao repository;

    public CreateReservationUseCaseImpl(ReservationDao repository){
        this.repository = repository;
    }

    @Override
    public void createReservation(RequestModel request) {
        Reservation reservation = Reservation.createReservation(
                request.startDate(),
                request.endDate(),
                request.owner(),
                request.room(),
                request.payment()
            );
        reservation.addGuest(reservation.getOwner());

        repository.save(reservation);
    }
}
