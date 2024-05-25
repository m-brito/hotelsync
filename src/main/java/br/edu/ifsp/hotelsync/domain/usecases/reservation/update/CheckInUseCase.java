package br.edu.ifsp.hotelsync.domain.usecases.reservation.update;

public interface CheckInUseCase {
    record RequestModel(Long id){}

    void doCheckIn(RequestModel request);
}
