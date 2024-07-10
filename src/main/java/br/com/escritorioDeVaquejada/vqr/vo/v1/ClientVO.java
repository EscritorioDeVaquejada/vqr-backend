package br.com.escritorioDeVaquejada.vqr.vo.v1;

import br.com.escritorioDeVaquejada.vqr.models.Address;
import br.com.escritorioDeVaquejada.vqr.models.Event;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class ClientVO {
    private UUID id;
    private String name;
    private String number;
    private String email;
    private Address address;

    public ClientVO() {
    }

    public ClientVO(UUID id, String name, String number, String email, List<Event> events, Address address) {
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
        ClientVO clientVO = (ClientVO) object;
        return Objects.equals(id, clientVO.id) && Objects.equals(name, clientVO.name) && Objects.equals(number, clientVO.number) && Objects.equals(email, clientVO.email) && Objects.equals(address, clientVO.address);
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


    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
