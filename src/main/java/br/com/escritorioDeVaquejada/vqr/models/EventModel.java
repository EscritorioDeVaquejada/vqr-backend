package br.com.escritorioDeVaquejada.vqr.models;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Table(name ="Eventos")
@Entity

public class EventModel implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id @GeneratedValue(strategy = GenerationType.UUID) @Column(name = "evento_id")
    private UUID id;
    private String name;
    private int startPasswords;
    private LocalDateTime dateTime;
    private Boolean isFinished;
    @Embedded
    private Address address;
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private ClientModel owner;
    @OneToMany(mappedBy = "eventId")
    List<TicketModel> tickets;
    @OneToOne()
    @JoinColumn(name = "financa_id")
    private FinanceModel financeRelatory;

    public EventModel(String name, int startPasswords, LocalDateTime dateTime, Address address, ClientModel owner, List<TicketModel> tickets, FinanceModel financeRelatory) {
        this.name = name;
        this.startPasswords = startPasswords;
        this.dateTime = dateTime;
        this.address = address;
        this.owner = owner;
        this.tickets = tickets;
        this.financeRelatory = financeRelatory;
    }

    public EventModel() {
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

    public ClientModel getOwner() {
        return owner;
    }

    public void setOwner(ClientModel owner) {
        this.owner = owner;
    }

    public List<TicketModel> getTickets() {
        return tickets;
    }

    public void setTickets(List<TicketModel> tickets) {
        this.tickets = tickets;
    }

}
