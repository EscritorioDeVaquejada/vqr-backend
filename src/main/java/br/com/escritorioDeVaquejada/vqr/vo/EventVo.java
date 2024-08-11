package br.com.escritorioDeVaquejada.vqr.vo;

import br.com.escritorioDeVaquejada.vqr.models.Address;
import jakarta.validation.constraints.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class EventVo implements Serializable {
    @Serial
    private static final long serialVersionUID=1L;
    private UUID id;
    @NotEmpty
    private String name;
    @PositiveOrZero
    private int startPasswords;
    private LocalDateTime dateTime;
    @NotNull
    private Address address;
    @PositiveOrZero
    private double defaultTicketPrice;
    @PositiveOrZero
    private double priceOfBoiTVAnticipated;
    @PositiveOrZero
    private double priceOfBoiTvPurchasedOnDemand;

    public double getTicketPrice() {
        return defaultTicketPrice;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        EventVo eventVo = (EventVo) object;
        return startPasswords == eventVo.startPasswords && Double.compare(defaultTicketPrice, eventVo.defaultTicketPrice) == 0 && Double.compare(priceOfBoiTVAnticipated, eventVo.priceOfBoiTVAnticipated) == 0 && Double.compare(priceOfBoiTvPurchasedOnDemand, eventVo.priceOfBoiTvPurchasedOnDemand) == 0 && Objects.equals(id, eventVo.id) && Objects.equals(name, eventVo.name) && Objects.equals(dateTime, eventVo.dateTime) && Objects.equals(address, eventVo.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, startPasswords, dateTime, address, defaultTicketPrice, priceOfBoiTVAnticipated, priceOfBoiTvPurchasedOnDemand);
    }

    public EventVo(UUID id, String name, int startPasswords, LocalDateTime dateTime, Address address, double defaultTicketPrice, double priceOfBoiTVAnticipated, double priceOfBoiTvPurchasedOnDemand) {
        this.id = id;
        this.name = name;
        this.startPasswords = startPasswords;
        this.dateTime = dateTime;
        this.address = address;
        this.defaultTicketPrice = defaultTicketPrice;
        this.priceOfBoiTVAnticipated = priceOfBoiTVAnticipated;
        this.priceOfBoiTvPurchasedOnDemand = priceOfBoiTvPurchasedOnDemand;
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

    public double getPriceOfBoiTvPurchasedOnDemand() {
        return priceOfBoiTvPurchasedOnDemand;
    }

    public void setPriceOfBoiTvPurchasedOnDemand(double priceOfBoiTvPurchasedOnDemand) {
        this.priceOfBoiTvPurchasedOnDemand = priceOfBoiTvPurchasedOnDemand;
    }

    public EventVo() {
    }

    public EventVo(UUID id, String name, int startPasswords, LocalDateTime dateTime, Address address) {
        this.id = id;
        this.name = name;
        this.startPasswords = startPasswords;
        this.dateTime = dateTime;
        this.address = address;
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

    public int getStartPasswords() {
        return startPasswords;
    }

    public void setStartPasswords(int startPasswords) {
        this.startPasswords = startPasswords;
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

}
