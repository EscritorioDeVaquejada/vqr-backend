package br.com.escritorioDeVaquejada.vqr.vo.ticket;

import br.com.escritorioDeVaquejada.vqr.enums.Status;
import br.com.escritorioDeVaquejada.vqr.vo.payment.PaymentResponseVO;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public class TicketDetailResponseVO implements Serializable {
    @Serial
    private static final long serialVersionUID=1L;
    private UUID id;
    private Integer number;
    private String cowboy;
    private String cowboyHorse;
    private String support;
    private String supportHorse;
    private String representation;
    private Boolean boiTV;
    private Status status;
    private String responsibleUserDuringPayment;
    private PaymentResponseVO payment;

    public TicketDetailResponseVO(UUID id, Integer number, String cowboy, String cowboyHorse, String support, String supportHorse, String representation, Boolean boiTV, Status status, String responsibleUserDuringPayment, PaymentResponseVO payment) {
        this.id = id;
        this.number = number;
        this.cowboy = cowboy;
        this.cowboyHorse = cowboyHorse;
        this.support = support;
        this.supportHorse = supportHorse;
        this.representation = representation;
        this.boiTV = boiTV;
        this.status = status;
        this.responsibleUserDuringPayment = responsibleUserDuringPayment;
        this.payment = payment;
    }

    public TicketDetailResponseVO() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getCowboy() {
        return cowboy;
    }

    public void setCowboy(String cowboy) {
        this.cowboy = cowboy;
    }

    public String getCowboyHorse() {
        return cowboyHorse;
    }

    public void setCowboyHorse(String cowboyHorse) {
        this.cowboyHorse = cowboyHorse;
    }

    public String getSupport() {
        return support;
    }

    public void setSupport(String support) {
        this.support = support;
    }

    public String getSupportHorse() {
        return supportHorse;
    }

    public void setSupportHorse(String supportHorse) {
        this.supportHorse = supportHorse;
    }

    public String getRepresentation() {
        return representation;
    }

    public void setRepresentation(String representation) {
        this.representation = representation;
    }

    public Boolean getBoiTV() {
        return boiTV;
    }

    public void setBoiTV(Boolean boiTV) {
        this.boiTV = boiTV;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getResponsibleUserDuringPayment() {
        return responsibleUserDuringPayment;
    }

    public void setResponsibleUserDuringPayment(String responsibleUserDuringPayment) {
        this.responsibleUserDuringPayment = responsibleUserDuringPayment;
    }

    public PaymentResponseVO getPayment() {
        return payment;
    }

    public void setPayment(PaymentResponseVO payment) {
        this.payment = payment;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        TicketDetailResponseVO that = (TicketDetailResponseVO) object;
        return Objects.equals(id, that.id) && Objects.equals(number, that.number) && Objects.equals(cowboy, that.cowboy) && Objects.equals(cowboyHorse, that.cowboyHorse) && Objects.equals(support, that.support) && Objects.equals(supportHorse, that.supportHorse) && Objects.equals(representation, that.representation) && Objects.equals(boiTV, that.boiTV) && status == that.status && Objects.equals(responsibleUserDuringPayment, that.responsibleUserDuringPayment) && Objects.equals(payment, that.payment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, number, cowboy, cowboyHorse, support, supportHorse, representation, boiTV, status, responsibleUserDuringPayment, payment);
    }
}
