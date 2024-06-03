package br.edu.ifsp.hotelsync.domain.usecases.guest.update;

import br.edu.ifsp.hotelsync.domain.entities.guest.Address;
import br.edu.ifsp.hotelsync.domain.entities.guest.Cpf;
import br.edu.ifsp.hotelsync.domain.entities.guest.Guest;
import br.edu.ifsp.hotelsync.domain.entities.guest.Phone;
import br.edu.ifsp.hotelsync.domain.persistence.dao.GuestDao;

import java.util.NoSuchElementException;

public class UpdateGuestUseCaseImpl implements UpdateGuestUseCase {
    private final GuestDao repository;

    public UpdateGuestUseCaseImpl(GuestDao repository) {
        this.repository = repository;
    }

    @Override
    public void updateOwner(OwnerRequestModel request) {
        if(!repository.existsByKey(request.id()))
            throw new NoSuchElementException("Guest of id " + request.id() + " not found");

        Phone phone = new Phone(request.phone());
        Cpf cpf = new Cpf(request.cpf());
        Address address = new Address(request.road(), request.city(), request.state(), request.cep(), request.district(), request.complement());
        Guest guest = Guest.createOwnerWithId(request.id(), request.name(), request.pronouns(), request.birthdate(), phone, cpf, address);

        repository.update(guest);
    }

    @Override
    public void updateGuest(GuestRequestModel request) {
        if(!repository.existsByKey(request.id()))
            throw new NoSuchElementException("Guest of id " + request.id() + " not found");

        Cpf cpf = new Cpf(request.cpf());
        Guest guest = Guest.createGuestWithId(request.id(), request.name(), request.birthdate(), cpf);

        repository.update(guest);
    }
}
