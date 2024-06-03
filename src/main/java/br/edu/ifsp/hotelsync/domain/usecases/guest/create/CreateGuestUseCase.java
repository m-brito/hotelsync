package br.edu.ifsp.hotelsync.domain.usecases.guest.create;

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
            String state,
            String cep,
            String district,
            String complement,
            String bank,
            String account,
            String agency,
            String cardDueDate,
            String cardOwner,
            String cardNumber
    ) {}

    record GuestRequestModel(
            String name,
            LocalDate birthdate,
            String cpf
    ){}

    Long createOwner(OwnerRequestModel requestModel);

    Long createGuest(GuestRequestModel requestModel);
}
