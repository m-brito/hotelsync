package br.edu.ifsp.hotelsync.domain.usecases.guest.update;

import br.edu.ifsp.hotelsync.domain.entities.guest.Address;
import br.edu.ifsp.hotelsync.domain.entities.guest.Cpf;
import br.edu.ifsp.hotelsync.domain.entities.guest.Guest;
import br.edu.ifsp.hotelsync.domain.entities.guest.Phone;
import br.edu.ifsp.hotelsync.domain.persistence.dao.GuestDAO;

import java.util.NoSuchElementException;

public class UpdateGuestUseCaseImpl implements UpdateGuestUseCase {
    private final GuestDAO repository;

    public UpdateGuestUseCaseImpl(GuestDAO repository) {
        this.repository = repository;
    }

    @Override
    public void updateGuest(RequestModel request) {
        if(!repository.existsByKey(request.id()))
            throw new NoSuchElementException("Guest of id " + request.id() + " not found");

        Phone phone = new Phone(request.phone());
        Cpf cpf = new Cpf(request.cpf());
        Address address = new Address(request.road(), request.city(), request.state(), request.cep(), request.district(), request.complement());
        Guest guest = new Guest(request.id(), request.name(), request.pronouns(), request.birthdate(), phone, cpf, address);

        repository.update(guest);
    }
}
