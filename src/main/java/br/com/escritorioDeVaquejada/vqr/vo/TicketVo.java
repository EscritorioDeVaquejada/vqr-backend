package br.com.escritorioDeVaquejada.vqr.vo;

import br.com.escritorioDeVaquejada.vqr.enums.Status;
import br.com.escritorioDeVaquejada.vqr.models.EventModel;
import br.com.escritorioDeVaquejada.vqr.models.PaymentModel;
import br.com.escritorioDeVaquejada.vqr.models.UserModel;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

import java.util.UUID;

public class TicketVo {
    private UUID id;
    private String cowboy;
    private String cowboyHorse;
    private String support;
    private String supportHorse;
    private String representation;
    private Boolean boiTv;
    private Status status;
    private EventModel event;
    private PaymentModel payment;
    private UserModel user;
}
