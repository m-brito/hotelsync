package br.edu.ifsp.hotelsync.domain.usecases.reservation.update;

public interface CheckOutUseCase {
    record RequestModel(Long id){}

    void doCheckOut(RequestModel request);
}
