package br.com.escritorioDeVaquejada.vqr.vo.ticket;

import br.com.escritorioDeVaquejada.vqr.enums.Status;
import br.com.escritorioDeVaquejada.vqr.vo.payment.PaymentResponseVO;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public class TicketRepresentationSummaryVO implements Serializable {
    @Serial
    private static final long serialVersionUID=1L;
    private UUID id;
    private Integer number;
    private String cowboy;
    private String cowboyHorse;
    private String representation;
    private Status status;

    public TicketRepresentationSummaryVO() {
    }

    public TicketRepresentationSummaryVO(UUID id, Integer number, String cowboy, String cowboyHorse, String representation, Status status) {
        this.id = id;
        this.number = number;
        this.cowboy = cowboy;
        this.cowboyHorse = cowboyHorse;
        this.representation = representation;
        this.status = status;
    }

    public UUID getId() {
        return id;
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

    public String getCowboy() {
        return cowboy;
    }

    public void setCowboy(String cowboy) {
        this.cowboy = cowboy;
    }

    public String getCowboyHorse() {
        return cowboyHorse;
    }

    public void setCowboyHorse(String cowboyHorse) {
        this.cowboyHorse = cowboyHorse;
    }

    public String getRepresentation() {
        return representation;
    }

    public void setRepresentation(String representation) {
        this.representation = representation;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object object) {

        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        TicketRepresentationSummaryVO that = (TicketRepresentationSummaryVO) object;
        return Objects.equals(id, that.id) && Objects.equals(number, that.number) && Objects.equals(cowboy, that.cowboy) && Objects.equals(cowboyHorse, that.cowboyHorse) && Objects.equals(representation, that.representation) && status == that.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, number, cowboy, cowboyHorse, representation, status);
    }
}
