package br.edu.ifsp.hotelsync.domain.entities.guest;

import br.edu.ifsp.hotelsync.domain.usecases.utils.Notification;

import java.time.LocalDate;
import java.util.Objects;

public class Guest {
    private Long id;
    private String name;
    private String pronouns;
    private LocalDate birthdate;
    private Phone phone;
    private Cpf cpf;
    private Address address;

    public static Guest createGuest(String name, LocalDate birthdate, Cpf cpf) {
        return new Guest(name, birthdate, cpf);
    }

    public static Guest createGuestWithId(Long id, String name, LocalDate birthdate, Cpf cpf){
        return new Guest(id, name, birthdate, cpf);
    }

    public static Guest createOwner(String name, String pronouns, LocalDate birthdate, Phone phone, Cpf cpf,
                             Address address) {
        return new Guest(name, pronouns, birthdate, phone, cpf, address);
    }

    public static Guest createOwnerWithId(Long id, String name, String pronouns, LocalDate birthdate, Phone phone, Cpf cpf,
                                     Address address) {
        return new Guest(id, name, pronouns, birthdate, phone, cpf, address);
    }

    private Guest(String name, LocalDate birthdate, Cpf cpf) {
        this.name = name;
        this.birthdate = birthdate;
        this.cpf = cpf;
        validateGuest();
    }

    private Guest(Long id, String name, LocalDate birthdate, Cpf cpf) {
        this.id = id;
        this.name = name;
        this.birthdate = birthdate;
        this.cpf = cpf;
        validateGuest();
    }

    private Guest(String name, String pronouns, LocalDate birthdate, Phone phone, Cpf cpf,
                 Address address) {
        this.name = name;
        this.pronouns = pronouns;
        this.birthdate = birthdate;
        this.phone = phone;
        this.cpf = cpf;
        this.address = address;
        validateOwner();
    }

    private Guest(Long id, String name, String pronouns, LocalDate birthdate, Phone phone, Cpf cpf,
                  Address address) {
        this.id = id;
        this.name = name;
        this.pronouns = pronouns;
        this.birthdate = birthdate;
        this.phone = phone;
        this.cpf = cpf;
        this.address = address;
        validateOwner();
    }

    private void validateOwner() {
        OwnerInputRequestValidator validator = new OwnerInputRequestValidator();
        Notification notification = validator.validate(this);

        if (notification.hasErrors())
            throw new IllegalArgumentException(notification.getErrorMessage());
    }

    private void validateGuest(){
        GuestInputRequestValidator validator = new GuestInputRequestValidator();
        Notification notification = validator.validate(this);

        if (notification.hasErrors())
            throw new IllegalArgumentException(notification.getErrorMessage());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPronouns() {
        return pronouns;
    }

    public void setPronouns(String pronouns) {
        this.pronouns = pronouns;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public Phone getPhone() {
        return phone;
    }

    public void setPhone(Phone phone) {
        this.phone = phone;
    }

    public Cpf getCpf() {
        return cpf;
    }

    public void setCpf(Cpf cpf) {
        this.cpf = cpf;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAddress(Cpf cpf) {
        this.cpf = cpf;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Guest guest = (Guest) o;
        return Objects.equals(cpf, guest.cpf);
    }


    @Override
    public String toString() {
        return getName();
    }

    @Override
    public int hashCode() {
        return Objects.hash(cpf);
    }
}
