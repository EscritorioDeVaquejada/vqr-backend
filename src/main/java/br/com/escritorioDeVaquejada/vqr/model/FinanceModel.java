package br.com.escritorioDeVaquejada.vqr.model;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "finances")
public class FinanceModel implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id @GeneratedValue(strategy = GenerationType.UUID) @Column(name = "finance_id")
    private UUID id;
    //todo alterar o nome do campo do eventId, pois não representa um ID na classe
    @OneToOne(mappedBy = "financialReport")
    private EventModel eventId;
    @Column(name = "total_pix")
    private Double totalPix;
    @Column(name = "total_credit")
    private Double totalCredit;
    @Column(name = "total_debit")
    private Double totalDebit;
    @Column(name = "total_cash")
    private Double totalCash;
    @Column(name = "total_tickets_sold")
    private int totalTicketsSold;
    @Column(name = "total_boi_tv")
    private int totalBoiTV;
    //todo qual a finalidade do campo totalFree
    @Column(name = "total_free")
    private int totalFree;

    public FinanceModel(Double totalPix, Double totalCredit, Double totalDebit, Double totalCash, int totalTicketsSold, int totalBoiTV, int totalFree) {
        this.totalPix = totalPix;
        this.totalCredit = totalCredit;
        this.totalDebit = totalDebit;
        this.totalCash = totalCash;
        this.totalTicketsSold = totalTicketsSold;
        this.totalBoiTV = totalBoiTV;
        this.totalFree = totalFree;
    }

    public FinanceModel() {
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        FinanceModel that = (FinanceModel) object;
        return totalTicketsSold == that.totalTicketsSold && totalBoiTV == that.totalBoiTV && totalFree == that.totalFree && Objects.equals(id, that.id) && Objects.equals(eventId, that.eventId) && Objects.equals(totalPix, that.totalPix) && Objects.equals(totalCredit, that.totalCredit) && Objects.equals(totalDebit, that.totalDebit) && Objects.equals(totalCash, that.totalCash);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, eventId, totalPix, totalCredit, totalDebit, totalCash, totalTicketsSold, totalBoiTV, totalFree);
    }

    public EventModel getEventId() {
        return eventId;
    }

    public void setEventId(EventModel eventId) {
        this.eventId = eventId;
    }

    public Double getTotalPix() {
        return totalPix;
    }

    public void setTotalPix(Double totalPix) {
        this.totalPix = totalPix;
    }

    public Double getTotalCredit() {
        return totalCredit;
    }

    public void setTotalCredit(Double totalCredit) {
        this.totalCredit = totalCredit;
    }

    public Double getTotalDebit() {
        return totalDebit;
    }

    public void setTotalDebit(Double totalDebit) {
        this.totalDebit = totalDebit;
    }

    public Double getTotalCash() {
        return totalCash;
    }

    public void setTotalCash(Double totalCash) {
        this.totalCash = totalCash;
    }

    public int getTotalTicketsSold() {
        return totalTicketsSold;
    }

    public void setTotalTicketsSold(int totalTicketsSold) {
        this.totalTicketsSold = totalTicketsSold;
    }

    public int getTotalBoiTV() {
        return totalBoiTV;
    }

    public void setTotalBoiTV(int totalBoiTV) {
        this.totalBoiTV = totalBoiTV;
    }

    public int getTotalFree() {
        return totalFree;
    }

    public void setTotalFree(int totalFree) {
        this.totalFree = totalFree;
    }

    public UUID getId() {
        return id;
    }
}
