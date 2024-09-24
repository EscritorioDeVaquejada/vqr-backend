package br.com.escritorioDeVaquejada.vqr.vo.payment;

import br.com.escritorioDeVaquejada.vqr.enums.PaymentMethod;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public class PaymentSaveVO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @NotNull
    private PaymentMethod paymentMethod;
    @NotNull @Min(0)
    private Double value;

    public PaymentSaveVO(PaymentMethod paymentMethod, Double value) {
        this.paymentMethod = paymentMethod;
        this.value = value;
    }

    public PaymentSaveVO() {
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

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        PaymentSaveVO that = (PaymentSaveVO) object;
        return paymentMethod == that.paymentMethod && Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(paymentMethod, value);
    }
}
