package br.com.escritorioDeVaquejada.vqr.services.impl;

import br.com.escritorioDeVaquejada.vqr.mappers.ModelMapper;
import br.com.escritorioDeVaquejada.vqr.models.EventModel;
import br.com.escritorioDeVaquejada.vqr.models.TicketModel;
import br.com.escritorioDeVaquejada.vqr.repositories.TicketRepository;
import br.com.escritorioDeVaquejada.vqr.services.TicketServices;
import br.com.escritorioDeVaquejada.vqr.vo.TicketVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TicketServicesImpl implements TicketServices {

    @Autowired
    private TicketRepository ticketRepository;

    public List<TicketModel> saveEmptyTickets(EventModel event){
        int totalNumberOfTickets = event.getStartPasswords();
        List<TicketModel> tickets = new ArrayList<>();
        for(int index = 0; index < totalNumberOfTickets; index++)
        {
            tickets.add(new TicketModel(event));
        }
        return ticketRepository.saveAll(tickets);
    }

}
