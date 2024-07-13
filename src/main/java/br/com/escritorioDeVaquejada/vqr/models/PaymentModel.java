package br.com.escritorioDeVaquejada.vqr.models;

import br.com.escritorioDeVaquejada.vqr.enums.PaymentMethod;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Table(name = "Pagamento")
@Entity
public class PaymentModel implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "pagamento_id")
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
