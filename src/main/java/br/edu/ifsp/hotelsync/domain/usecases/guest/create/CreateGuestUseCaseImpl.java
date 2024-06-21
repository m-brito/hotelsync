package br.edu.ifsp.hotelsync.domain.usecases.guest.create;

import br.edu.ifsp.hotelsync.domain.entities.guest.*;
import br.edu.ifsp.hotelsync.domain.persistence.dao.GuestDao;

public class CreateGuestUseCaseImpl implements CreateGuestUseCase {
    private final GuestDao guestRepository;


    public CreateGuestUseCaseImpl(GuestDao guestRepository) {
        this.guestRepository = guestRepository;

    }

    @Override
    public Long createOwner(OwnerRequestModel request) {
        Phone phone = new Phone(request.phone());
        Cpf cpf = new Cpf(request.cpf());
        Address address = new Address(request.road(), request.city(), State.valueOf(String.valueOf(request.state())), request.cep(), request.district(), request.complement());
        Guest guest = Guest.createOwner(request.name(), request.pronouns(), request.birthdate(), phone, cpf, address);

        return guestRepository.save(guest);
    }


    @Override
    public Long createGuest(GuestRequestModel request) {
        Cpf cpf = new Cpf(request.cpf());
        Guest guest = Guest.createGuest(request.name(), request.birthdate(), cpf);
        return guestRepository.save(guest);
    }
}
