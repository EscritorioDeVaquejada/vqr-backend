package br.com.escritorioDeVaquejada.vqr.models;

import br.com.escritorioDeVaquejada.vqr.enums.PaymentMethod;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Table(name = "Pagamento")
@Entity
public class Payment {

    @Id
    @Column(name = "pagamento_id")
    private UUID id;
    private PaymentMethod paymentMethod;
    private Double value;
    private LocalDateTime dateTime;
    @OneToOne(mappedBy = "paymentId")
    private Ticket ticketId;

    public Payment(PaymentMethod paymentMethod, Double value, LocalDateTime dateTime, Ticket ticketId) {
        this.paymentMethod = paymentMethod;
        this.value = value;
        this.dateTime = dateTime;
        this.ticketId = ticketId;
    }

    public Payment() {
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

    public Ticket getTicketId() {
        return ticketId;
    }

    public void setTicketId(Ticket ticketId) {
        this.ticketId = ticketId;
    }
}
