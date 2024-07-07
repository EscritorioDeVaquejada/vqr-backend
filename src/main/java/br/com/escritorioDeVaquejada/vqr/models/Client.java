package br.com.escritorioDeVaquejada.vqr.models;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;
@Table(name ="Clientes")
@Entity
public class Client implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name="cliente_id")
    private UUID id;
    private String name;
    private String number;
    private String email;
    @OneToMany(mappedBy = "owner")
    private List<Event> events;
    @Embedded
    private Address address;

    public Client(String name, String number, String email, List<Event> events, Address address) {
        this.name = name;
        this.number = number;
        this.email = email;
        this.events = events;
        this.address = address;
    }

    public Client() {
    }

    public UUID getId() {
        return id;
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

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
