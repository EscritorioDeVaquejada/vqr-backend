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
    @Id@Column(name = "finance_id")
    private UUID id;
    @OneToOne(mappedBy = "financialReport")
    private EventModel eventId;
    private Double totalPix;
    private Double totalCredit;
    private Double totalDebit;
    private Double totalCash;
    private int totalSelledTickets;
    private int totalBoiTv;
    private int totalfree;

    public FinanceModel(EventModel eventId, Double totalPix, Double totalCredit, Double totalDebit, Double totalCash, int totalSelledTickets, int totalBoiTv, int totalfree) {
        this.eventId = eventId;
        this.totalPix = totalPix;
        this.totalCredit = totalCredit;
        this.totalDebit = totalDebit;
        this.totalCash = totalCash;
        this.totalSelledTickets = totalSelledTickets;
        this.totalBoiTv = totalBoiTv;
        this.totalfree = totalfree;
    }

    public FinanceModel() {
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        FinanceModel that = (FinanceModel) object;
        return totalSelledTickets == that.totalSelledTickets && totalBoiTv == that.totalBoiTv && totalfree == that.totalfree && Objects.equals(id, that.id) && Objects.equals(eventId, that.eventId) && Objects.equals(totalPix, that.totalPix) && Objects.equals(totalCredit, that.totalCredit) && Objects.equals(totalDebit, that.totalDebit) && Objects.equals(totalCash, that.totalCash);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, eventId, totalPix, totalCredit, totalDebit, totalCash, totalSelledTickets, totalBoiTv, totalfree);
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

    public int getTotalSelledTickets() {
        return totalSelledTickets;
    }

    public void setTotalSelledTickets(int totalSelledTickets) {
        this.totalSelledTickets = totalSelledTickets;
    }

    public int getTotalBoiTv() {
        return totalBoiTv;
    }

    public void setTotalBoiTv(int totalBoiTv) {
        this.totalBoiTv = totalBoiTv;
    }

    public int getTotalfree() {
        return totalfree;
    }

    public void setTotalfree(int totalfree) {
        this.totalfree = totalfree;
    }

    public UUID getId() {
        return id;
    }
}
