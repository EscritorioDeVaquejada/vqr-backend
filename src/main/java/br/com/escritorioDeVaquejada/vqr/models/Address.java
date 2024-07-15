package br.com.escritorioDeVaquejada.vqr.models;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotEmpty;

@Embeddable
public class Address {
    @NotEmpty
    private String state;
    @NotEmpty
    private String city;

    public Address(String state, String city) {
        this.state = state;
        this.city = city;
    }

    public Address() {
    }


    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}