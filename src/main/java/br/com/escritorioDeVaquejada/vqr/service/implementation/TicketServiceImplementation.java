package br.com.escritorioDeVaquejada.vqr.service.implementation;

import br.com.escritorioDeVaquejada.vqr.enums.Status;
import br.com.escritorioDeVaquejada.vqr.exception.ResourceNotFoundException;
import br.com.escritorioDeVaquejada.vqr.mapper.Mapper;
import br.com.escritorioDeVaquejada.vqr.model.EventModel;
import br.com.escritorioDeVaquejada.vqr.model.TicketModel;
import br.com.escritorioDeVaquejada.vqr.repository.EventRepository;
import br.com.escritorioDeVaquejada.vqr.repository.TicketRepository;
import br.com.escritorioDeVaquejada.vqr.service.EventService;
import br.com.escritorioDeVaquejada.vqr.service.TicketService;
import br.com.escritorioDeVaquejada.vqr.vo.ticket.TicketDetailResponseVO;
import br.com.escritorioDeVaquejada.vqr.vo.ticket.TicketRepresentationSummaryVO;
import br.com.escritorioDeVaquejada.vqr.vo.ticket.TicketStatusResponseVO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class TicketServiceImplementation implements TicketService {
    private final TicketRepository ticketRepository;
    //Para evitar referências circulares
    private final EventRepository eventRepository;
    private final Mapper mapper;

    @Autowired
    public TicketServiceImplementation(
            TicketRepository ticketRepository,
            EventRepository eventRepository,
            Mapper mapper) {
        this.eventRepository = eventRepository;
        this.ticketRepository = ticketRepository;
        this.mapper = mapper;
    }

    public List<TicketModel> saveEmptyTickets(EventModel event) {
        int totalNumberOfTickets = event.getNumberOfInitialTickets();
        List<TicketModel> tickets = new ArrayList<>();
        for (int index = 1; index <= totalNumberOfTickets; index++) {
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

    @Override
    public TicketDetailResponseVO findTicketDetailsById(UUID ticketId) throws ResourceNotFoundException {
        TicketModel ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new ResourceNotFoundException("Ticket not found!"));
        return mapper.parseObject(ticket, TicketDetailResponseVO.class);
    }

    @Override
    @Transactional
    public Page<TicketStatusResponseVO> findTicketStatusByEventId(UUID eventId, Pageable pageable) throws ResourceNotFoundException{
        EventModel event = eventRepository.findById(eventId)
                .orElseThrow(() -> new ResourceNotFoundException("Event not found!"));
        Page<TicketModel> ticketModelsPage = ticketRepository.findByEvent(event, pageable);
        return ticketModelsPage.map(
                ticketModel -> mapper.parseObject(ticketModel, TicketStatusResponseVO.class));
    }

    @Override
    @Transactional
    public Page<TicketRepresentationSummaryVO> findTicketRepresentationSummaryByEventId(UUID eventId, Pageable pageable) {
        EventModel event = eventRepository.findById(eventId)
                .orElseThrow(() -> new ResourceNotFoundException("Event not found!"));
        Page<TicketModel> ticketModelsPage = ticketRepository.findByEvent(event, pageable);
        return ticketModelsPage.map(
                ticketModel -> mapper.parseObject(ticketModel, TicketRepresentationSummaryVO.class));

    }

}
