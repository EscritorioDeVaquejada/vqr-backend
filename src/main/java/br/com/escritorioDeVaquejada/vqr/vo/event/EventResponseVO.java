package br.com.escritorioDeVaquejada.vqr.vo.event;

import br.com.escritorioDeVaquejada.vqr.model.Address;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Schema(description = "Represents the data returned in HTTP responses for event-related operations. " +
        "This object is used to send event information in HTTP responses.")
public class EventResponseVO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private UUID id;
    @NotBlank
    private String name;
    @PositiveOrZero
    private int numberOfInitialTickets;
    private LocalDateTime dateTime;
    @NotNull
    private Address address;
    @PositiveOrZero
    private double defaultTicketPrice;
    @PositiveOrZero
    private double priceOfBoiTVAnticipated;
    @PositiveOrZero
    private double priceOfBoiTVPurchasedOnDemand;

    public EventResponseVO() {
    }

    public EventResponseVO(
            UUID id,
            String name,
            int numberOfInitialTickets,
            LocalDateTime dateTime,
            Address address,
            double defaultTicketPrice,
            double priceOfBoiTVAnticipated,
            double priceOfBoiTVPurchasedOnDemand) {
        this.id = id;
        this.name = name;
        this.numberOfInitialTickets = numberOfInitialTickets;
        this.dateTime = dateTime;
        this.address = address;
        this.defaultTicketPrice = defaultTicketPrice;
        this.priceOfBoiTVAnticipated = priceOfBoiTVAnticipated;
        this.priceOfBoiTVPurchasedOnDemand = priceOfBoiTVPurchasedOnDemand;
    }

    /*
    public EventResponseVO(UUID id, String name, int numberOfInitialTickets, LocalDateTime dateTime, Address address) {
        this.id = id;
        this.name = name;
        this.numberOfInitialTickets = numberOfInitialTickets;
        this.dateTime = dateTime;
        this.address = address;
    }
     */

    public double getTicketPrice() {
        return defaultTicketPrice;
    }

    public void setTicketPrice(double defaultTicketPrice) {
        this.defaultTicketPrice = defaultTicketPrice;
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

    public int getNumberOfInitialTickets() {
        return numberOfInitialTickets;
    }

    public void setNumberOfInitialTickets(int numberOfInitialTickets) {
        this.numberOfInitialTickets = numberOfInitialTickets;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        EventResponseVO eventResponseVo = (EventResponseVO) object;
        return numberOfInitialTickets == eventResponseVo.numberOfInitialTickets && Double.compare(defaultTicketPrice, eventResponseVo.defaultTicketPrice) == 0 && Double.compare(priceOfBoiTVAnticipated, eventResponseVo.priceOfBoiTVAnticipated) == 0 && Double.compare(priceOfBoiTVPurchasedOnDemand, eventResponseVo.priceOfBoiTVPurchasedOnDemand) == 0 && Objects.equals(id, eventResponseVo.id) && Objects.equals(name, eventResponseVo.name) && Objects.equals(dateTime, eventResponseVo.dateTime) && Objects.equals(address, eventResponseVo.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, numberOfInitialTickets, dateTime, address, defaultTicketPrice, priceOfBoiTVAnticipated, priceOfBoiTVPurchasedOnDemand);
    }
}
