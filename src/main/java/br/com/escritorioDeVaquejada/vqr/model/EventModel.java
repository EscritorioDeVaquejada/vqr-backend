package br.com.escritorioDeVaquejada.vqr.model;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Table(name ="events")
@Entity
public class EventModel implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id @GeneratedValue(strategy = GenerationType.UUID) @Column(name = "event_id")
    private UUID id;
    @Column(nullable = false)
    private String name;
    @Column(name="number_of_initial_tickets", nullable = false)
    private int numberOfInitialTickets;
    @Column(name="date_time", nullable = false)
    private LocalDateTime dateTime;
    @Column(name="is_finished")
    private Boolean isFinished;
    @Column(name="default_ticket_price", nullable = false)
    private double defaultTicketPrice;
    @Column(name="price_of_boi_tv_anticipated", nullable = false)
    private double priceOfBoiTVAnticipated;
    @Column(name="price_of_boi_tv_purchased_on_demand", nullable = false)
    private double priceOfBoiTVPurchasedOnDemand;
    @Embedded @Column(nullable = false)
    private Address address;
    @ManyToOne @JoinColumn(name = "client_id")
    private ClientModel owner;
    @OneToMany(mappedBy = "event")
    List<TicketModel> tickets;
    @OneToOne() @JoinColumn(name = "finance_id")
    private FinanceModel financialReport;

    public EventModel(UUID id, String name, int numberOfInitialTickets, LocalDateTime dateTime, Boolean isFinished, double defaultTicketPrice, double priceOfBoiTVAnticipated, double priceOfBoiTVPurchasedOnDemand, Address address, ClientModel owner, List<TicketModel> tickets, FinanceModel financialReport) {
        this.id = id;
        this.name = name;
        this.numberOfInitialTickets = numberOfInitialTickets;
        this.dateTime = dateTime;
        this.isFinished = isFinished;
        this.defaultTicketPrice = defaultTicketPrice;
        this.priceOfBoiTVAnticipated = priceOfBoiTVAnticipated;
        this.priceOfBoiTVPurchasedOnDemand = priceOfBoiTVPurchasedOnDemand;
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
        return numberOfInitialTickets == that.numberOfInitialTickets && Double.compare(defaultTicketPrice, that.defaultTicketPrice) == 0 && Double.compare(priceOfBoiTVAnticipated, that.priceOfBoiTVAnticipated) == 0 && Double.compare(priceOfBoiTVPurchasedOnDemand, that.priceOfBoiTVPurchasedOnDemand) == 0 && Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(dateTime, that.dateTime) && Objects.equals(isFinished, that.isFinished) && Objects.equals(address, that.address) && Objects.equals(owner, that.owner) && Objects.equals(tickets, that.tickets) && Objects.equals(financialReport, that.financialReport);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, numberOfInitialTickets, dateTime, isFinished, defaultTicketPrice, priceOfBoiTVAnticipated, priceOfBoiTVPurchasedOnDemand, address, owner, tickets, financialReport);
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

    public double getPriceOfBoiTVPurchasedOnDemand() {
        return priceOfBoiTVPurchasedOnDemand;
    }

    public void setPriceOfBoiTVPurchasedOnDemand(double priceOfBoiTVPurchasedOnDemand) {
        this.priceOfBoiTVPurchasedOnDemand = priceOfBoiTVPurchasedOnDemand;
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