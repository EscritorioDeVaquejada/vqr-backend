package br.com.escritorioDeVaquejada.vqr.vo.event;

import br.com.escritorioDeVaquejada.vqr.model.Address;
import br.com.escritorioDeVaquejada.vqr.vo.address.AddressVO;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
//todo verificar se as anotações @NotNull em conjunto com @Min(0) substituem @PositiveOrZero(), na classe de testes
@Schema(description = "Represents the data needed to create or update a event. " +
        "This object is used to receive event information in HTTP requests.")
public class EventRequestVO implements Serializable {
    @Serial
    static private final long serialVersionUID = 1L;
    @NotBlank
    @Schema(description = "Name of the event.", example = "Summer Rodeo")
    private String name;
    @NotNull @Min(0)
    @Schema(description = "Initial number of tickets available for the event.", example = "100")
    private int numberOfInitialTickets;
    @NotNull
    private AddressVO address;
    @NotNull @Min(0)
    @Schema(description = "Standard price of a ticket for the event.", example = "1000.00")
    private double defaultTicketPrice;
    @NotNull @Min(0)
    @Schema(description = "Payment amount for BoiTV in advance.", example = "300.00")
    private double priceOfBoiTVAnticipated;
    @NotNull @Min(0)
    @Schema(description = "Payment amount for BoiTV made on demand.", example = "400.00")
    private double priceOfBoiTVPurchasedOnDemand;

    public EventRequestVO() {
    }

    public EventRequestVO(
            String name,
            int numberOfInitialTickets,
            AddressVO address,
            double defaultTicketPrice,
            double priceOfBoiTVAnticipated,
            double priceOfBoiTVPurchasedOnDemand) {
        this.name = name;
        this.numberOfInitialTickets = numberOfInitialTickets;
        this.address = address;
        this.defaultTicketPrice = defaultTicketPrice;
        this.priceOfBoiTVAnticipated = priceOfBoiTVAnticipated;
        this.priceOfBoiTVPurchasedOnDemand = priceOfBoiTVPurchasedOnDemand;
    }

    public double getDefaultTicketPrice() {
        return defaultTicketPrice;
    }

    public void setDefaultTicketPrice(double defaultTicketPrice) {
        this.defaultTicketPrice = defaultTicketPrice;
    }

    public double getPriceOfBoiTVAnticipated() {
        return priceOfBoiTVAnticipated;
    }

    public void setPriceOfBoiTVAnticipated(double priceOfBoiTVAnticipated) {
        this.priceOfBoiTVAnticipated = priceOfBoiTVAnticipated;
    }

    public double getPriceOfBoiTVPurchasedOnDemand() {
        return priceOfBoiTVPurchasedOnDemand;
    }

    public void setPriceOfBoiTVPurchasedOnDemand(double priceOfBoiTVPurchasedOnDemand) {
        this.priceOfBoiTVPurchasedOnDemand = priceOfBoiTVPurchasedOnDemand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberOfInitialTickets() {
        return numberOfInitialTickets;
    }

    public void setNumberOfInitialTickets(int numberOfInitialTickets) {
        this.numberOfInitialTickets = numberOfInitialTickets;
    }

    public AddressVO getAddress() {
        return address;
    }

    public void setAddress(AddressVO address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        EventRequestVO that = (EventRequestVO) object;
        return numberOfInitialTickets == that.numberOfInitialTickets && Double.compare(defaultTicketPrice, that.defaultTicketPrice) == 0 && Double.compare(priceOfBoiTVAnticipated, that.priceOfBoiTVAnticipated) == 0 && Double.compare(priceOfBoiTVPurchasedOnDemand, that.priceOfBoiTVPurchasedOnDemand) == 0 && Objects.equals(name, that.name) && Objects.equals(address, that.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, numberOfInitialTickets, address, defaultTicketPrice, priceOfBoiTVAnticipated, priceOfBoiTVPurchasedOnDemand);
    }
}
