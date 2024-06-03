package br.edu.ifsp.hotelsync.domain.usecases.reservation.update.interfaces;

public interface RemoveGuestUseCase {
    record RequestModel(Long idGuest, Long idReservation){}

    void removeGuest(RequestModel request);
}
