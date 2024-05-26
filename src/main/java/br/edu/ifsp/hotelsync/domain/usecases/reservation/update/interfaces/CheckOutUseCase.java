package br.edu.ifsp.hotelsync.domain.usecases.reservation.update.interfaces;

public interface CheckOutUseCase {
    record RequestModel(Long id){}

    void doCheckOut(RequestModel request);
}
