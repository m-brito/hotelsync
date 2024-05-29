package br.edu.ifsp.hotelsync.domain.usecases.reservation.update.interfaces;

import br.edu.ifsp.hotelsync.domain.persistence.dao.GuestDAO;

public interface AddNewGuestUseCase {
    record RequestModel(Long idGuest, Long idReservation){}

    void addNewGuest(Long idGuest, Long idReservation);
}
