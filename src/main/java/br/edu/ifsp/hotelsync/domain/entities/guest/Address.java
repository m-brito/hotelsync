package br.edu.ifsp.hotelsync.domain.entities.guest;

public record Address(String road,
        String city,
        String state,
        String cep,
        String district,
        String complement) {

    public String getRoad() {
        return road;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getCep() {
        return cep;
    }

    public String getDistrict() {
        return district;
    }

    public String getComplement() {
        return complement;
    }

}
