package br.com.escritorioDeVaquejada.vqr.service;

import br.com.escritorioDeVaquejada.vqr.model.EventModel;
import br.com.escritorioDeVaquejada.vqr.model.FinanceModel;
import br.com.escritorioDeVaquejada.vqr.model.PaymentModel;
import br.com.escritorioDeVaquejada.vqr.model.TicketModel;

public interface FinanceService {
    FinanceModel createFinance();
    void updateFinanceByTicketSale(EventModel event, TicketModel ticket,
                                   PaymentModel ticketPayment);
}
