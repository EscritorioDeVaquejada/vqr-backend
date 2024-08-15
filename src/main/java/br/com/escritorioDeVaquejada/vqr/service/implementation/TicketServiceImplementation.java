package br.com.escritorioDeVaquejada.vqr.service.implementation;

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

    @Autowired
    private TicketRepository ticketRepository;

    public List<TicketModel> saveEmptyTickets(EventModel event){
        int totalNumberOfTickets = event.getStartPasswords();
        List<TicketModel> tickets = new ArrayList<>();
        for(int index = 0; index < totalNumberOfTickets; index++){
            tickets.add(new TicketModel(event));
        }
        return ticketRepository.saveAll(tickets);
    }

}
