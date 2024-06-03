package br.edu.ifsp.hotelsync.domain.usecases.reservation.update.interfaces;

public interface AddNewGuestUseCase {
    record RequestModel(Long idGuest, Long idReservation){}

    void addNewGuest(RequestModel request);
}
