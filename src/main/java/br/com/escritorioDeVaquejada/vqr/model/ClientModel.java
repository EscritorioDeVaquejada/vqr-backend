package br.com.escritorioDeVaquejada.vqr.model;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Table(name ="clients")
@Entity
public class ClientModel implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name="client_id")
    private UUID id;
    private String name;
    private String number;
    @Column(unique = true)
    private String email;
    @OneToMany(mappedBy = "owner")
    private List<EventModel> events;
    @Embedded
    private Address address;

    public ClientModel(String name, String number, String email, List<EventModel> events, Address address) {
        this.name = name;
        this.number = number;
        this.email = email;
        this.events = events;
        this.address = address;
    }

    public ClientModel() {
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        ClientModel that = (ClientModel) object;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(number, that.number) && Objects.equals(email, that.email) && Objects.equals(events, that.events) && Objects.equals(address, that.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, number, email, events, address);
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public List<EventModel> getEvents() {
        return events;
    }

    public void setEvents(List<EventModel> events) {
        this.events = events;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}