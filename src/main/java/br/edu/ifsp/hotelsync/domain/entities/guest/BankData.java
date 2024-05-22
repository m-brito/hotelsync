package br.edu.ifsp.hotelsync.domain.entities.guest;

public class BankData {
    private Long id;
    private String bank;
    private String account;
    private String agency;
    private String cardDueDate;
    private String cardOwner;
    private String cardNumber;

    public BankData(Long id, String bank, String account, String agency, String cardDueDate, String cardOwner, String cardNumber) {
        this.id = id;
        this.bank = bank;
        this.account = account;
        this.agency = agency;
        this.cardDueDate = cardDueDate;
        this.cardOwner = cardOwner;
        this.cardNumber = cardNumber;
    }

    public BankData(String bank, String account, String agency, String cardDueDate, String cardOwner, String cardNumber) {
        this.bank = bank;
        this.account = account;
        this.agency = agency;
        this.cardDueDate = cardDueDate;
        this.cardOwner = cardOwner;
        this.cardNumber = cardNumber;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getAgency() {
        return agency;
    }

    public void setAgency(String agency) {
        this.agency = agency;
    }

    public String getCardDueDate() {
        return cardDueDate;
    }

    public void setCardDueDate(String cardDueDate) {
        this.cardDueDate = cardDueDate;
    }

    public String getCardOwner() {
        return cardOwner;
    }

    public void setCardOwner(String cardOwner) {
        this.cardOwner = cardOwner;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }
}
