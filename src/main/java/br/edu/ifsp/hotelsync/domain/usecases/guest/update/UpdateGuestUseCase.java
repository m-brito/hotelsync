package br.edu.ifsp.hotelsync.domain.usecases.guest.update;

import java.time.LocalDate;

public interface UpdateGuestUseCase {
    record OwnerRequestModel(
            Long id,
            String name,
            String pronouns,
            LocalDate birthdate,
            String phone,
            String cpf,
            String road,
            String city,
            String state,
            String cep,
            String district,
            String complement
    ) {}

    record GuestRequestModel(
            Long id,
            String name,
            LocalDate birthdate,
            String cpf
    ){}

    void updateOwner(OwnerRequestModel requestModel);
    void updateGuest(GuestRequestModel requestModel);
}
