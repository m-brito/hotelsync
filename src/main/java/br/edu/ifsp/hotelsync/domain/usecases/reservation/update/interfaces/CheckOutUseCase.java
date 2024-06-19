package br.edu.ifsp.hotelsync.domain.usecases.reservation.update.interfaces;

import br.edu.ifsp.hotelsync.domain.entities.reservation.Payment;

public interface CheckOutUseCase {
    record RequestModel(Long id, Payment paymentMethod){}

    void doCheckOut(RequestModel request);
}
