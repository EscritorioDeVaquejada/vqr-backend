package br.com.escritorioDeVaquejada.vqr.vo.address;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

import java.io.Serial;
import java.io.Serializable;

@Schema(description = "Represents an address containing information about the state and city " +
        "of a given location.")
public class AddressVO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @NotBlank
    @Schema(description = "The state where the address is located.", example = "California")
    private String state;
    @NotBlank
    @Schema(description = "The city where the address is located.", example = "Los Angeles")
    private String city;

    public AddressVO() {
    }

    public AddressVO(String state, String city) {
        this.state = state;
        this.city = city;
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
