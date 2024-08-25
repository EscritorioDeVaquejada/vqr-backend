package br.com.escritorioDeVaquejada.vqr.vo.ticket;

import br.com.escritorioDeVaquejada.vqr.enums.Status;
import br.com.escritorioDeVaquejada.vqr.model.EventModel;
import br.com.escritorioDeVaquejada.vqr.model.PaymentModel;
import br.com.escritorioDeVaquejada.vqr.model.UserModel;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

public class TicketVO implements Serializable {
    @Serial
    private static final long serialVersionUID=1L;
    private UUID id;
    private String cowboy;
    private String cowboyHorse;
    private String support;
    private String supportHorse;
    private String representation;
    private Boolean boiTV;
    private Status status;
    private EventModel event;
    private PaymentModel payment;
    private UserModel user;
}
