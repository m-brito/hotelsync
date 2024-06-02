package br.edu.ifsp.hotelsync.domain.usecases.guest.create;

import br.edu.ifsp.hotelsync.domain.entities.guest.*;
import br.edu.ifsp.hotelsync.domain.persistence.dao.BankDataDAO;
import br.edu.ifsp.hotelsync.domain.persistence.dao.GuestDAO;

public class CreateGuestUseCaseImpl implements CreateGuestUseCase {
    private final GuestDAO guestRepository;


    public CreateGuestUseCaseImpl(GuestDAO guestRepository) {
        this.guestRepository = guestRepository;

    }

    @Override
    public Long createGuest(RequestModel request) {
        Phone phone = new Phone(request.phone());
        Cpf cpf = new Cpf(request.cpf());
        Address address = new Address(request.road(), request.city(), request.state(), request.cep(), request.district(), request.complement());
        Guest guest = new Guest(request.name(), request.pronouns(), request.birthdate(), phone, cpf, address);

        return guestRepository.save(guest);
    }
}
