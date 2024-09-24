package br.com.escritorioDeVaquejada.vqr.model;

import br.com.escritorioDeVaquejada.vqr.enums.Status;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;
@Entity
@Table(name="tickets")
public class TicketModel implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id @GeneratedValue(strategy = GenerationType.UUID) @Column(name = "ticket_id")
    private UUID id;
    @Column(unique = true)
    private Integer number;
    private String cowboy;
    @Column(name = "cowboy_horse")
    private String cowboyHorse;
    private String support;
    @Column(name = "support_horse")
    private String supportHorse;
    private String representation;
    @Column(name = "boi_tv")
    private Boolean boiTV;
    private Status status;
    @ManyToOne
    @JoinColumn(name = "event_id")
    private EventModel event;
    @OneToOne
    @JoinColumn(name = "payment_id")
    private PaymentModel payment;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserModel user;

    public TicketModel(EventModel event) {
        this.cowboy = null;
        this.cowboyHorse = null;
        this.support = null;
        this.supportHorse = null;
        this.representation = null;
        this.boiTV = false;
        this.status = Status.LIVRE;
        this.event = event;
        this.payment = null;
        this.user = null;
    }

    public TicketModel(
            Integer number,
            String cowboy,
            String cowboyHorse,
            String support,
            String supportHorse,
            String representation,
            Boolean boiTV,
            Status status,
            EventModel event,
            PaymentModel payment,
            UserModel user) {
        this.number = number;
        this.cowboy = cowboy;
        this.cowboyHorse = cowboyHorse;
        this.support = support;
        this.supportHorse = supportHorse;
        this.representation = representation;
        this.boiTV = boiTV;
        this.status = status;
        this.event = event;
        this.payment = payment;
        this.user = user;
    }

    public TicketModel() {
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getSupport() {
        return support;
    }

    public void setSupport(String support) {
        this.support = support;
    }

    public String getSupportHorse() {
        return supportHorse;
    }

    public void setSupportHorse(String supportHorse) {
        this.supportHorse = supportHorse;
    }

    public PaymentModel getPayment() {
        return payment;
    }

    public void setPayment(PaymentModel payment) {
        this.payment = payment;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
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

    public String getRepresentation() {
        return representation;
    }

    public void setRepresentation(String representation) {
        this.representation = representation;
    }

    public Boolean getBoiTV() {
        return boiTV;
    }

    public void setBoiTV(Boolean boiTV) {
        this.boiTV = boiTV;
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

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        TicketModel that = (TicketModel) object;
        return Objects.equals(id, that.id) && Objects.equals(number, that.number) && Objects.equals(cowboy, that.cowboy) && Objects.equals(cowboyHorse, that.cowboyHorse) && Objects.equals(support, that.support) && Objects.equals(supportHorse, that.supportHorse) && Objects.equals(representation, that.representation) && Objects.equals(boiTV, that.boiTV) && status == that.status && Objects.equals(event, that.event) && Objects.equals(payment, that.payment) && Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, number, cowboy, cowboyHorse, support, supportHorse, representation, boiTV, status, event, payment, user);
    }
}
