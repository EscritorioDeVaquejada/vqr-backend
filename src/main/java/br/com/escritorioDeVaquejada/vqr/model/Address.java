package br.com.escritorioDeVaquejada.vqr.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;

@Embeddable
@Schema(description = "Represents an address with a state and city.")
public class Address {
    @NotBlank
    @Schema(description = "The state where the address is located.", example = "California")
    private String state;
    @NotBlank
    @Schema(description = "The city where the address is located.", example = "Los Angeles")
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