package br.edu.ifsp.hotelsync.domain.usecases.reservation.update;

public interface CancelReservationUseCase {
    record RequestModel(Long id){}

    void cancelReservation(CheckInUseCase.RequestModel request);
}
