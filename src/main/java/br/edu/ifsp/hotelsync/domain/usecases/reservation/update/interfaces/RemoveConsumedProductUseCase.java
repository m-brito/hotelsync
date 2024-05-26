package br.edu.ifsp.hotelsync.domain.usecases.reservation.update.interfaces;

public interface RemoveConsumedProductUseCase {

    record RequestModel(Long idReservation, Long idProduct, int quantity){}

    void removeConsumedProduct(RequestModel request);
}
