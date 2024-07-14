package br.com.escritorioDeVaquejada.vqr.vo;

import br.com.escritorioDeVaquejada.vqr.models.Address;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;
import java.util.UUID;

public class ClientVo {
    private UUID id;
    @NotEmpty
    private String name;
    @NotEmpty
    private String number;
    private String email;
    @NotNull @Valid
    private Address address;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientVo clientVo = (ClientVo) o;
        return Objects.equals(id, clientVo.id) && Objects.equals(name, clientVo.name) && Objects.equals(number, clientVo.number) && Objects.equals(email, clientVo.email) && Objects.equals(address, clientVo.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, number, email, address);
    }

    public ClientVo() {
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
