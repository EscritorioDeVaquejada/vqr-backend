package br.com.escritorioDeVaquejada.vqr.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;

@Embeddable
public class Address {
    @NotBlank
    private String state;
    @NotBlank
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