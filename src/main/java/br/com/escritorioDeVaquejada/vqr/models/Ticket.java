package br.com.escritorioDeVaquejada.vqr.models;

import br.com.escritorioDeVaquejada.vqr.enums.Status;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;
@Entity
@Table(name="Senhas")
public class Ticket implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id @GeneratedValue(strategy = GenerationType.UUID)@Column(name = "ticket_id")
    private UUID id;
    private String cowboy;
    private String cowboyHorse;
    private String suport;
    private String suportHorse;
    private String representation;
    private Boolean boiTv;
    private Status status;
    @ManyToOne
    @JoinColumn(name = "evento_id")
    private Event eventId;
    @OneToOne
    @JoinColumn(name = "pagamento_id")
    private Payment paymentId;

    //todo Criar e colocar user


    public Ticket(String cowboy, String cowboyHorse, String suport, String suportHorse, String representation, Boolean boiTv, Status status, Event eventId, Payment paymentId) {
        this.cowboy = cowboy;
        this.cowboyHorse = cowboyHorse;
        this.suport = suport;
        this.suportHorse = suportHorse;
        this.representation = representation;
        this.boiTv = boiTv;
        this.status = status;
        this.eventId = eventId;
        this.paymentId = paymentId;
    }

    public Ticket() {
    }

    public UUID getId() {
        return id;
    }

    public String getCowboy() {
        return cowboy;
    }

    public void setCowboy(String cowboy) {
        this.cowboy = cowboy;
    }

    public String getCowboyHorse() {
        return cowboyHorse;
    }

    public void setCowboyHorse(String vowboyHorse) {
        this.cowboyHorse = vowboyHorse;
    }

    public String getSuport() {
        return suport;
    }

    public void setSuport(String suport) {
        this.suport = suport;
    }

    public String getSuportHorse() {
        return suportHorse;
    }

    public void setSuportHorse(String suportHorse) {
        this.suportHorse = suportHorse;
    }

    public String getRepresentation() {
        return representation;
    }

    public void setRepresentation(String representation) {
        this.representation = representation;
    }

    public Boolean getBoiTv() {
        return boiTv;
    }

    public void setBoiTv(Boolean boiTv) {
        this.boiTv = boiTv;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Event getEventId() {
        return eventId;
    }

    public void setEventId(Event eventId) {
        this.eventId = eventId;
    }
}
