package br.com.escritorioDeVaquejada.vqr.vo.client;

import br.com.escritorioDeVaquejada.vqr.vo.address.AddressVO;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Schema(description = "Represents the data returned in HTTP responses for client-related operations. " +
        "This object is used to send client information in HTTP responses.")
public class ClientDetailResponseVO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Schema(description = "Unique identifier of the client.",
            example = "123e4567-e89b-12d3-a456-426614174000")
    private UUID id;
    @Schema(description = "Full name of the client.", example = "John Doe")
    private String name;
    @Schema(description = "Client contact number.", example = "88999999999")
    private String number;
    @Schema(description = "Client contact email.", example = "john_doe@email.com")
    private String email;
    //todo verificar necessidade do endereço do cliente ser obrigatório
    private AddressVO address;

    public ClientDetailResponseVO() {
    }

    public ClientDetailResponseVO(UUID id, String name, String number, String email, AddressVO address) {
        this.id = id;
        this.name = name;
        this.number = number;
        this.email = email;
        this.address = address;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        ClientDetailResponseVO that = (ClientDetailResponseVO) object;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(number, that.number) && Objects.equals(email, that.email) && Objects.equals(address, that.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, number, email, address);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public AddressVO getAddress() {
        return address;
    }

    public void setAddress(AddressVO address) {
        this.address = address;
    }
}
