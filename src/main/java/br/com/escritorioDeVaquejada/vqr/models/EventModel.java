package br.com.escritorioDeVaquejada.vqr.models;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Table(name ="Events")
@Entity
public class EventModel implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "event_id")
    private UUID id;
    private String name;
    private int startPasswords;
    private LocalDateTime dateTime;
    private Boolean isFinished;
    private double defaultTicketPrice;
    private double priceOfBoiTVAnticipated;
    private double priceOfBoiTvPurchasedOnDemand;
    @Embedded
    private Address address;
    @ManyToOne
    @JoinColumn(name = "client_id")
    private ClientModel owner;
    @OneToMany(mappedBy = "event")
    List<TicketModel> tickets;
    @OneToOne()
    @JoinColumn(name = "financa_id")
    private FinanceModel financialReport;

    public EventModel(UUID id, String name, int startPasswords, LocalDateTime dateTime, Boolean isFinished, double defaultTicketPrice, double priceOfBoiTVAnticipated, double priceOfBoiTvPurchasedOnDemand, Address address, ClientModel owner, List<TicketModel> tickets, FinanceModel financialReport) {
        this.id = id;
        this.name = name;
        this.startPasswords = startPasswords;
        this.dateTime = dateTime;
        this.isFinished = isFinished;
        this.defaultTicketPrice = defaultTicketPrice;
        this.priceOfBoiTVAnticipated = priceOfBoiTVAnticipated;
        this.priceOfBoiTvPurchasedOnDemand = priceOfBoiTvPurchasedOnDemand;
        this.address = address;
        this.owner = owner;
        this.tickets = tickets;
        this.financialReport = financialReport;
    }

    public EventModel() {
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        EventModel that = (EventModel) object;
        return startPasswords == that.startPasswords && Double.compare(defaultTicketPrice, that.defaultTicketPrice) == 0 && Double.compare(priceOfBoiTVAnticipated, that.priceOfBoiTVAnticipated) == 0 && Double.compare(priceOfBoiTvPurchasedOnDemand, that.priceOfBoiTvPurchasedOnDemand) == 0 && Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(dateTime, that.dateTime) && Objects.equals(isFinished, that.isFinished) && Objects.equals(address, that.address) && Objects.equals(owner, that.owner) && Objects.equals(tickets, that.tickets) && Objects.equals(financialReport, that.financialReport);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, startPasswords, dateTime, isFinished, defaultTicketPrice, priceOfBoiTVAnticipated, priceOfBoiTvPurchasedOnDemand, address, owner, tickets, financialReport);
    }

    public double getDefaultTicketPrice() {
        return defaultTicketPrice;
    }

    public void setDefaultTicketPrice(double defaultTicketPrice) {
        this.defaultTicketPrice = defaultTicketPrice;
    }

    public FinanceModel getFinancialReport() {
        return financialReport;
    }

    public void setFinancialReport(FinanceModel financialReport) {
        this.financialReport = financialReport;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Boolean getFinished() {
        return isFinished;
    }

    public void setFinished(Boolean finished) {
        isFinished = finished;
    }

    public double getTicketPrice() {
        return defaultTicketPrice;
    }

    public void setTicketPrice(double defaultTicketPrice) {
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

    public FinanceModel getFinanceRelatory() {
        return financialReport;
    }

    public void setFinanceRelatory(FinanceModel financialReport) {
        this.financialReport = financialReport;
    }

    public UUID getId() {
        return id;
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

    public ClientModel getOwner() {
        return owner;
    }

    public void setOwner(ClientModel owner) {
        this.owner = owner;
    }

    public List<TicketModel> getTickets() {
        return tickets;
    }

    public void setTickets(List<TicketModel> tickets) {
        this.tickets = tickets;
    }

}