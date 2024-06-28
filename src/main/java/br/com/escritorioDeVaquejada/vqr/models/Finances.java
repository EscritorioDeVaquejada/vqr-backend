package br.com.escritorioDeVaquejada.vqr.models;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "financas")
public class Finances {
    @Id@Column(name = "financas_id")
    private UUID id;
    @OneToOne(mappedBy = "financeRelatory")
    private Event eventId;
    private Double totalPix;
    private Double totalCredit;
    private Double totalDebit;
    private Double totalCash;
    private int totalSelledTickets;
    private int totalBoiTv;
    private int totalfree;

    public Finances(Event eventId, Double totalPix, Double totalCredit, Double totalDebit, Double totalCash, int totalSelledTickets, int totalBoiTv, int totalfree) {
        this.eventId = eventId;
        this.totalPix = totalPix;
        this.totalCredit = totalCredit;
        this.totalDebit = totalDebit;
        this.totalCash = totalCash;
        this.totalSelledTickets = totalSelledTickets;
        this.totalBoiTv = totalBoiTv;
        this.totalfree = totalfree;
    }

    public Finances() {
    }

    public Event getEventId() {
        return eventId;
    }

    public void setEventId(Event eventId) {
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
