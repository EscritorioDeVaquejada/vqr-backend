package br.com.escritorioDeVaquejada.vqr.service.implementation;

import br.com.escritorioDeVaquejada.vqr.enums.PaymentMethod;
import br.com.escritorioDeVaquejada.vqr.exception.ResourceNotFoundException;
import br.com.escritorioDeVaquejada.vqr.model.EventModel;
import br.com.escritorioDeVaquejada.vqr.model.FinanceModel;
import br.com.escritorioDeVaquejada.vqr.model.PaymentModel;
import br.com.escritorioDeVaquejada.vqr.model.TicketModel;
import br.com.escritorioDeVaquejada.vqr.repository.FinanceRepository;
import br.com.escritorioDeVaquejada.vqr.service.EventService;
import br.com.escritorioDeVaquejada.vqr.service.FinanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.events.Event;

@Service
public class FinanceServiceImplementation implements FinanceService {
    private final FinanceRepository financeRepository;

    @Autowired
    public FinanceServiceImplementation(
            FinanceRepository financeRepository) {
        this.financeRepository = financeRepository;
    }

    public FinanceModel createFinance(){
        FinanceModel financeToBeCreated = new FinanceModel(
                0.0,
                0.0,
                0.0,
                0.0,
                0,
                0,
                0
        );
        return financeRepository.save(financeToBeCreated);
    }

    @Override
    public void updateFinanceByTicketSale(
            EventModel event,
            TicketModel ticket,
            PaymentModel ticketPayment)
    {

    }
}
