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

@Schema(description = "Represents the data needed to create or update a event. " +
        "This object is used to receive event information in HTTP requests.")
public class EventRequestVO implements Serializable {
    @Serial
    static private final long serialVersionUID = 1L;
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

    public EventRequestVO() {
    }

    public EventRequestVO(
            String name,
            int numberOfInitialTickets,
            LocalDateTime dateTime,
            Address address,
            double defaultTicketPrice,
            double priceOfBoiTVAnticipated,
            double priceOfBoiTVPurchasedOnDemand) {
        this.name = name;
        this.numberOfInitialTickets = numberOfInitialTickets;
        this.dateTime = dateTime;
        this.address = address;
        this.defaultTicketPrice = defaultTicketPrice;
        this.priceOfBoiTVAnticipated = priceOfBoiTVAnticipated;
        this.priceOfBoiTVPurchasedOnDemand = priceOfBoiTVPurchasedOnDemand;
    }

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
        EventRequestVO that = (EventRequestVO) object;
        return numberOfInitialTickets == that.numberOfInitialTickets && Double.compare(defaultTicketPrice, that.defaultTicketPrice) == 0 && Double.compare(priceOfBoiTVAnticipated, that.priceOfBoiTVAnticipated) == 0 && Double.compare(priceOfBoiTVPurchasedOnDemand, that.priceOfBoiTVPurchasedOnDemand) == 0 && Objects.equals(name, that.name) && Objects.equals(dateTime, that.dateTime) && Objects.equals(address, that.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, numberOfInitialTickets, dateTime, address, defaultTicketPrice, priceOfBoiTVAnticipated, priceOfBoiTVPurchasedOnDemand);
    }
}
