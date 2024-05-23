package br.edu.ifsp.hotelsync.domain.usecases.guest.find;

import br.edu.ifsp.hotelsync.domain.entities.guest.Guest;

public interface FindOneGuestUseCase {
    record RequestModel(Long id) {}

    Guest findOneById(RequestModel request);

}
