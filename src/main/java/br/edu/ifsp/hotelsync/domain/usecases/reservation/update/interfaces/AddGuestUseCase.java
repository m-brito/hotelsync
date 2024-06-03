package br.edu.ifsp.hotelsync.domain.usecases.reservation.update.interfaces;

public interface AddGuestUseCase {
    record RequestModel(Long idGuest, Long idReservation){}

    void addGuest(RequestModel request);
}
