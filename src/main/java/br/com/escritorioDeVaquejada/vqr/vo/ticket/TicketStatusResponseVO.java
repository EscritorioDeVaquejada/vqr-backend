package br.com.escritorioDeVaquejada.vqr.vo.ticket;

import br.com.escritorioDeVaquejada.vqr.enums.Status;
import br.com.escritorioDeVaquejada.vqr.vo.payment.PaymentResponseVO;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public class TicketStatusResponseVO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private UUID id;
    private Integer number;
    private Status status;

    public TicketStatusResponseVO(UUID id, Integer number, Status status) {
        this.id = id;
        this.number = number;
        this.status = status;
    }

    public TicketStatusResponseVO() {
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
        TicketStatusResponseVO that = (TicketStatusResponseVO) object;
        return Objects.equals(id, that.id) && Objects.equals(number, that.number) && status == that.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, number, status);
    }
}
