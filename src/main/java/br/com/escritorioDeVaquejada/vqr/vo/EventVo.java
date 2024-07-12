package br.com.escritorioDeVaquejada.vqr.vo;

import br.com.escritorioDeVaquejada.vqr.models.Address;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class EventVo {
    private UUID id;
    @NotEmpty
    private String name;
    @PositiveOrZero
    private int startPasswords;
    private LocalDateTime dateTime;
    @NotNull
    private Address address;

    public EventVo() {
    }

    public EventVo(UUID id, String name, int startPasswords, LocalDateTime dateTime, Address address) {
        this.id = id;
        this.name = name;
        this.startPasswords = startPasswords;
        this.dateTime = dateTime;
        this.address = address;
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

    public int getStartPasswords() {
        return startPasswords;
    }

    public void setStartPasswords(int startPasswords) {
        this.startPasswords = startPasswords;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        EventVo eventVo = (EventVo) object;
        return startPasswords == eventVo.startPasswords && Objects.equals(id, eventVo.id) && Objects.equals(name, eventVo.name) && Objects.equals(dateTime, eventVo.dateTime) && Objects.equals(address, eventVo.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, startPasswords, dateTime, address);
    }
}
