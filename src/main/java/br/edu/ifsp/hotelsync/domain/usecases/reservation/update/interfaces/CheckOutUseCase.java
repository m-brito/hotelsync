package br.edu.ifsp.hotelsync.domain.usecases.reservation.update.interfaces;

public interface CheckOutUseCase {
    record RequestModel(Long id, String paymentMethod){}

    void doCheckOut(RequestModel request);
}
