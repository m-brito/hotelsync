package br.edu.ifsp.hotelsync.domain.usecases.reservation.update.interfaces;

public interface AddConsumedProductUseCase {

    record RequestModel(Long idReservation, Long idProduct, int quantity){}

    void addConsumedProduct(RequestModel request);
}
