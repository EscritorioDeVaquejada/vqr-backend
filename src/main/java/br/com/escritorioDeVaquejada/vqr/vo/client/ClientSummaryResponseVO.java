package br.com.escritorioDeVaquejada.vqr.vo.client;

import br.com.escritorioDeVaquejada.vqr.vo.address.AddressVO;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

//todo adiconar campo par url de imagem
public class ClientSummaryResponseVO implements Serializable{
        @Serial
        private static final long serialVersionUID = 1L;
        @Schema(description = "Unique identifier of the client.",
                example = "123e4567-e89b-12d3-a456-426614174000")
        private UUID id;
        @Schema(description = "Full name of the client.", example = "John Doe")
        private String name;
        private Integer eventNumbers;

    public ClientSummaryResponseVO(UUID id, String name, Integer eventNumbers) {
        this.id = id;
        this.name = name;
        this.eventNumbers = eventNumbers;
    }

    public ClientSummaryResponseVO() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getEventNumbers() {
        return eventNumbers;
    }

    public void setEventNumbers(Integer eventNumbers) {
        this.eventNumbers = eventNumbers;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        ClientSummaryResponseVO that = (ClientSummaryResponseVO) object;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(eventNumbers, that.eventNumbers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, eventNumbers);
    }
}
