package br.com.escritorioDeVaquejada.vqr.services;

import br.com.escritorioDeVaquejada.vqr.models.EventModel;
import br.com.escritorioDeVaquejada.vqr.models.TicketModel;
import br.com.escritorioDeVaquejada.vqr.vo.TicketVo;

import java.util.List;


public interface TicketServices {
    List<TicketModel> saveEmptyTickets(EventModel event);
}