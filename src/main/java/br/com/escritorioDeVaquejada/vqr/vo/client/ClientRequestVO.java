package br.com.escritorioDeVaquejada.vqr.vo.client;

import br.com.escritorioDeVaquejada.vqr.model.Address;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Schema(description = "Represents the data needed to create or update a client. " +
        "This object is used to receive client information in HTTP requests.")
public class ClientRequestVO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Schema(description = "Full name of the client", example = "John Doe")
    @NotBlank(message = "Name cannot be null or an empty string")
    private String name;
    @Schema(description = "Client contact number", example = "88999999999")
    @NotBlank(message = "Number cannot be null or an empty string")
    private String number;
    @Schema(description = "Client contact email", example = "john_doe@email.com")
    private String email;
    //todo verificar necessidade do endereço do cliente ser obrigatório
    @Schema(description = "Client address")
    @NotNull(message = "Address cannot be null")
    @Valid
    private Address address;

    public ClientRequestVO() {
    }

    public ClientRequestVO(String name, String number, String email, Address address) {
        this.name = name;
        this.number = number;
        this.email = email;
        this.address = address;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        ClientRequestVO that = (ClientRequestVO) object;
        return Objects.equals(name, that.name) && Objects.equals(number, that.number) && Objects.equals(email, that.email) && Objects.equals(address, that.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, number, email, address);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
