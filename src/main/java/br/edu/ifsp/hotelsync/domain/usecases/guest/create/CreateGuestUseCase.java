package br.edu.ifsp.hotelsync.domain.usecases.guest.create;

import br.edu.ifsp.hotelsync.domain.entities.guest.State;

import java.time.LocalDate;

public interface CreateGuestUseCase {
    record OwnerRequestModel(
            String name,
            String pronouns,
            LocalDate birthdate,
            String phone,
            String cpf,
            String road,
            String city,
            State state,
            String cep,
            String district,
            String complement
    ) {}

    record GuestRequestModel(
            String name,
            LocalDate birthdate,
            String cpf
    ){}

    Long createOwner(OwnerRequestModel requestModel);

    Long createGuest(GuestRequestModel requestModel);
}
