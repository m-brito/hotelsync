package br.edu.ifsp.hotelsync.domain.entities.guest;

import java.time.LocalDate;

public class Guest {
    private Long id;
    private String name;
    private String pronouns;
    private LocalDate birthdate;
    private Phone phone;
    private Cpf cpf;
    private Address address;
    private BankData bankData;
    private boolean isActive = true;

    public Guest(Long id, String name, String pronouns, LocalDate birthdate, Phone phone, Cpf cpf, Address address, BankData bankData) {
        this.id = id;
        this.name = name;
        this.pronouns = pronouns;
        this.birthdate = birthdate;
        this.phone = phone;
        this.cpf = cpf;
        this.address = address;
        this.bankData = bankData;
    }

    public Guest(String name, String pronouns, LocalDate birthdate, Phone phone, Cpf cpf, Address address, BankData bankData) {
        this.name = name;
        this.pronouns = pronouns;
        this.birthdate = birthdate;
        this.phone = phone;
        this.cpf = cpf;
        this.address = address;
        this.bankData = bankData;
    }

    public void deactivate(){
        isActive = false;
    }

    public void activate(){
        isActive = true;
    }

    public boolean isActive() {
        return isActive;
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

    public BankData getCreditData() {
        return bankData;
    }

    public void setCreditData(BankData bankData) {
        this.bankData = bankData;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BankData getBankData() {
        return bankData;
    }

    public void setBankData(BankData bankData) {
        this.bankData = bankData;
    }
}
