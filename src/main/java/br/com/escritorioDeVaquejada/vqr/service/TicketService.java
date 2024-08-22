package br.com.escritorioDeVaquejada.vqr.service;

import br.com.escritorioDeVaquejada.vqr.model.EventModel;
import br.com.escritorioDeVaquejada.vqr.model.TicketModel;

import java.util.List;


public interface TicketService {
    List<TicketModel> saveEmptyTickets(EventModel event);
}