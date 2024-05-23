package br.edu.ifsp.hotelsync.domain.usecases.guest.create;

import java.time.LocalDate;

public interface CreateGuestUseCase {
    record RequestModel(
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
            boolean isActive,
            String bank,
            String account,
            String agency,
            String cardDueDate,
            String cardOwner,
            String cardNumber
    ) {}

    Long createGuest(RequestModel requestModel);
}
