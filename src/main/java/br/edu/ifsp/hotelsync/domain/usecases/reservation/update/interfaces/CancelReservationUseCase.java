package br.edu.ifsp.hotelsync.domain.usecases.reservation.update.interfaces;

public interface CancelReservationUseCase {
    record RequestModel(Long id){}

    void cancelReservation(CheckInUseCase.RequestModel request);
}
