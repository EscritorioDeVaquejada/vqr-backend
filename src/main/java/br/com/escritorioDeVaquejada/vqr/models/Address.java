package br.com.escritorioDeVaquejada.vqr.models;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;

@Embeddable
public class Address {
    private String state;
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
