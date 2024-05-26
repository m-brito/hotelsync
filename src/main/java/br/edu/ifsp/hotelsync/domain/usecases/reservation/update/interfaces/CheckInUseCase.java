package br.edu.ifsp.hotelsync.domain.usecases.reservation.update.interfaces;

public interface CheckInUseCase {
    record RequestModel(Long id){}

    void doCheckIn(RequestModel request);
}
