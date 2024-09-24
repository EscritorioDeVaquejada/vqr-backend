package br.com.escritorioDeVaquejada.vqr.vo.client;

import br.com.escritorioDeVaquejada.vqr.model.Address;
import br.com.escritorioDeVaquejada.vqr.vo.address.AddressVO;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Schema(description = "Represents the data needed to update a client partially. " +
        "This object is used to receive client information in HTTP requests for partially updating " +
        "an existing client using HTTP PATCH. Only the fields included in this object will be updated.")
public class ClientPatchVO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Schema(description = "Full name of the client.", example = "John Doe") @NotEmpty
    private String name;
    @Schema(description = "Client contact number.", example = "88999999999") @NotEmpty
    private String number;
    @Schema(description = "Client contact email", example = "john_doe@email.com")
    private String email;
    @Valid
    private AddressVO address;

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

    public AddressVO getAddress() {
        return address;
    }

    public void setAddress(AddressVO address) {
        this.address = address;
    }

    public ClientPatchVO(String name, String number, String email, AddressVO address) {
        this.name = name;
        this.number = number;
        this.email = email;
        this.address = address;
    }

    public ClientPatchVO() {
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        ClientPatchVO that = (ClientPatchVO) object;
        return Objects.equals(name, that.name) && Objects.equals(number, that.number) && Objects.equals(email, that.email) && Objects.equals(address, that.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, number, email, address);
    }
}
