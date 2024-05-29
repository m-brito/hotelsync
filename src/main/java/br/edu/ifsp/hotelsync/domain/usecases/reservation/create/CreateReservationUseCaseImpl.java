package br.edu.ifsp.hotelsync.domain.usecases.reservation.create;

import br.edu.ifsp.hotelsync.domain.entities.reservation.Reservation;
import br.edu.ifsp.hotelsync.domain.persistence.dao.ReservationDAO;

public class CreateReservationUseCaseImpl implements CreateReservationUseCase{

    private final ReservationDAO repository;

    public CreateReservationUseCaseImpl(ReservationDAO repository){
        this.repository = repository;
    }

    @Override
    public void createReservation(RequestModel request) {
        Reservation reservation = new Reservation(
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
