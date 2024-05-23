package br.edu.ifsp.hotelsync.domain.usecases.guest.update;

import java.time.LocalDate;

public interface UpdateGuestUseCase {
    record RequestModel(
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
            String complement,
            boolean isActive,
            String bank,
            String account,
            String agency,
            String cardDueDate,
            String cardOwner,
            String cardNumber
    ) {}

    void updateGuest(RequestModel requestModel);
}
