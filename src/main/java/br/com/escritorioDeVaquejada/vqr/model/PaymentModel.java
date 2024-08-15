package br.com.escritorioDeVaquejada.vqr.model;

import br.com.escritorioDeVaquejada.vqr.enums.PaymentMethod;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Table(name = "payments")
@Entity
public class PaymentModel implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "payment_id")
    private UUID id;
    private PaymentMethod paymentMethod;
    private Double value;
    private LocalDateTime dateTime;
    @OneToOne(mappedBy = "payment")
    private TicketModel ticketId;

    public PaymentModel(PaymentMethod paymentMethod, Double value, LocalDateTime dateTime, TicketModel ticketId) {
        this.paymentMethod = paymentMethod;
        this.value = value;
        this.dateTime = dateTime;
        this.ticketId = ticketId;
    }

    public PaymentModel() {
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        PaymentModel that = (PaymentModel) object;
        return Objects.equals(id, that.id) && paymentMethod == that.paymentMethod && Objects.equals(value, that.value) && Objects.equals(dateTime, that.dateTime) && Objects.equals(ticketId, that.ticketId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, paymentMethod, value, dateTime, ticketId);
    }

    public UUID getId() {
        return id;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public TicketModel getTicketId() {
        return ticketId;
    }

    public void setTicketId(TicketModel ticketId) {
        this.ticketId = ticketId;
    }
}
