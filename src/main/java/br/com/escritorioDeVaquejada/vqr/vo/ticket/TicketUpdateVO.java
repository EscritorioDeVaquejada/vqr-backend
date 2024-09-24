package br.com.escritorioDeVaquejada.vqr.vo.ticket;

import br.com.escritorioDeVaquejada.vqr.vo.payment.PaymentSaveVO;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public class TicketUpdateVO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @NotBlank
    private String username;
    @NotNull @Min(0)
    private Integer number;
    @NotBlank
    private String cowboy;
    @NotBlank
    private String cowboyHorse;
    @NotBlank
    private String support;
    @NotBlank
    private String supportHorse;
    @NotBlank
    private String representation;
    @NotNull
    private Boolean boiTV;
    @NotNull
    private PaymentSaveVO payment;

    public TicketUpdateVO() {
    }

    public TicketUpdateVO(String username, Integer number, String cowboy, String cowboyHorse, String support, String supportHorse, String representation, Boolean boiTV, PaymentSaveVO payment) {
        this.username = username;
        this.number = number;
        this.cowboy = cowboy;
        this.cowboyHorse = cowboyHorse;
        this.support = support;
        this.supportHorse = supportHorse;
        this.representation = representation;
        this.boiTV = boiTV;
        this.payment = payment;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public PaymentSaveVO getPayment() {
        return payment;
    }

    public void setPayment(PaymentSaveVO payment) {
        this.payment = payment;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        TicketUpdateVO that = (TicketUpdateVO) object;
        return Objects.equals(username, that.username) && Objects.equals(number, that.number) && Objects.equals(cowboy, that.cowboy) && Objects.equals(cowboyHorse, that.cowboyHorse) && Objects.equals(support, that.support) && Objects.equals(supportHorse, that.supportHorse) && Objects.equals(representation, that.representation) && Objects.equals(boiTV, that.boiTV) && Objects.equals(payment, that.payment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, number, cowboy, cowboyHorse, support, supportHorse, representation, boiTV, payment);
    }
}
