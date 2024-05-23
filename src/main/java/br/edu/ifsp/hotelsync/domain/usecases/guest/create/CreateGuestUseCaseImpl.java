package br.edu.ifsp.hotelsync.domain.usecases.guest.create;

import br.edu.ifsp.hotelsync.domain.entities.guest.*;
import br.edu.ifsp.hotelsync.domain.persistence.dao.BankDataDAO;
import br.edu.ifsp.hotelsync.domain.persistence.dao.GuestDAO;

public class CreateGuestUseCaseImpl implements CreateGuestUseCase {
    private final GuestDAO guestRepository;
    private final BankDataDAO bankDataRepository;

    public CreateGuestUseCaseImpl(GuestDAO guestRepository, BankDataDAO bankDataRepository) {
        this.guestRepository = guestRepository;
        this.bankDataRepository = bankDataRepository;
    }

    @Override
    public Long createGuest(RequestModel request) {
        Phone phone = new Phone(request.phone());
        Cpf cpf = new Cpf(request.cpf());
        Address address = new Address(request.road(), request.city(), request.state(), request.cep(), request.district(), request.complement());
        BankData bankData = new BankData(request.bank(), request.account(), request.agency(), request.cardDueDate(), request.cardOwner(), request.cardNumber());
        Guest guest = new Guest(request.name(), request.pronouns(), request.birthdate(), phone, cpf, address, bankData);

        bankDataRepository.save(bankData);
        return guestRepository.save(guest);
    }
}
