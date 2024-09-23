package br.com.escritorioDeVaquejada.vqr.service.implementation;

import br.com.escritorioDeVaquejada.vqr.enums.Status;
import br.com.escritorioDeVaquejada.vqr.model.EventModel;
import br.com.escritorioDeVaquejada.vqr.model.TicketModel;
import br.com.escritorioDeVaquejada.vqr.repository.TicketRepository;
import br.com.escritorioDeVaquejada.vqr.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TicketServiceImplementation implements TicketService {
    private final TicketRepository ticketRepository;

    @Autowired
    public TicketServiceImplementation(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public List<TicketModel> saveEmptyTickets(EventModel event){
        int totalNumberOfTickets = event.getNumberOfInitialTickets();
        List<TicketModel> tickets = new ArrayList<>();
        for(int index = 1; index <= totalNumberOfTickets; index++){
            tickets.add(new TicketModel(
                    index,
                    null,
                    null,
                    null,
                    null,
                    null,
                    false,
                    Status.LIVRE,
                    event,
                    null,
                    null
            ));
            //tickets.add(new TicketModel(event));
        }
        return ticketRepository.saveAll(tickets);
    }

}
