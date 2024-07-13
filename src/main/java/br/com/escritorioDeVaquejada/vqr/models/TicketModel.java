package br.com.escritorioDeVaquejada.vqr.models;

import br.com.escritorioDeVaquejada.vqr.enums.Status;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;
@Entity
@Table(name="Senhas")
public class TicketModel implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id @GeneratedValue(strategy = GenerationType.UUID)@Column(name = "ticket_id")
    private UUID id;
    private String cowboy;
    private String cowboyHorse;
    private String support;
    private String supportHorse;
    private String representation;
    private Boolean boiTv;
    private Status status;
    @ManyToOne
    @JoinColumn(name = "evento_id")
    private EventModel event;
    @OneToOne
    @JoinColumn(name = "pagamento_id")
    private PaymentModel payment;
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private UserModel user;

    public TicketModel(
            EventModel event
            /*PaymentModel payment*,/
            /*UserModel user*/) {
        this.cowboy = null;
        this.cowboyHorse = null;
        this.support = null;
        this.supportHorse = null;
        this.representation = null;
        this.boiTv = false;
        this.status = Status.LIVRE;
        this.event = event;
        this.payment = null;
        this.user = null;
    }

    public TicketModel() {
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
        return support;
    }

    public void setSuport(String support) {
        this.support = support;
    }

    public String getSuportHorse() {
        return supportHorse;
    }

    public void setSuportHorse(String supportHorse) {
        this.supportHorse = supportHorse;
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

    public EventModel getEvent() {
        return event;
    }

    public void setEvent(EventModel event) {
        this.event = event;
    }
}
