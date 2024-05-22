package br.edu.ifsp.hotelsync.domain.entities.guest;

public class Address {
    private String road;
    private String city;
    private String state;
    private String cep;
    private String district;
    private String complement;

    public Address(String road, String city, String state, String cep, String district, String complement) {
        this.road = road;
        this.city = city;
        this.state = state;
        this.cep = cep;
        this.district = district;
        this.complement = complement;
    }

    public String getRoad() {
        return road;
    }

    public void setRoad(String road) {
        this.road = road;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }
}
