package br.com.escritorioDeVaquejada.vqr.vo.payment;

import br.com.escritorioDeVaquejada.vqr.enums.PaymentMethod;
import br.com.escritorioDeVaquejada.vqr.model.TicketModel;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class PaymentResponseVO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private UUID id;
    private PaymentMethod paymentMethod;
    private Double value;
    private LocalDateTime dateTime;

    public PaymentResponseVO(UUID id, PaymentMethod paymentMethod, Double value, LocalDateTime dateTime) {
        this.id = id;
        this.paymentMethod = paymentMethod;
        this.value = value;
        this.dateTime = dateTime;
    }

    public PaymentResponseVO() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        PaymentResponseVO that = (PaymentResponseVO) object;
        return Objects.equals(id, that.id) && paymentMethod == that.paymentMethod && Objects.equals(value, that.value) && Objects.equals(dateTime, that.dateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, paymentMethod, value, dateTime);
    }
}
