package br.com.escritorioDeVaquejada.vqr.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Table(name ="Eventos")
@Entity

public class Event {
    @Id @GeneratedValue(strategy = GenerationType.UUID) @Column(name = "evento_id")
    private UUID id;
    private String name;
    private int startPasswords;
    private LocalDateTime dateTime;
    @Embedded
    private Address address;
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Client owner;
    @OneToMany(mappedBy = "eventId")
    List<Ticket> tickets;
    @OneToOne()
    @JoinColumn(name = "financa_id")
    private Finance financeRelatory;

    public Event(String name, int startPasswords, LocalDateTime dateTime, Address address, Client owner, List<Ticket> tickets, Finance financeRelatory) {
        this.name = name;
        this.startPasswords = startPasswords;
        this.dateTime = dateTime;
        this.address = address;
        this.owner = owner;
        this.tickets = tickets;
        this.financeRelatory = financeRelatory;
    }

    public Event() {
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

    public Client getOwner() {
        return owner;
    }

    public void setOwner(Client owner) {
        this.owner = owner;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

}
