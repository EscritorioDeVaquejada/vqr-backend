package br.com.escritorioDeVaquejada.vqr.service.implementation;


import br.com.escritorioDeVaquejada.vqr.exception.ResourceNotFoundException;
import br.com.escritorioDeVaquejada.vqr.mapper.Mapper;
import br.com.escritorioDeVaquejada.vqr.model.ClientModel;
import br.com.escritorioDeVaquejada.vqr.model.EventModel;
import br.com.escritorioDeVaquejada.vqr.repository.EventRepository;
import br.com.escritorioDeVaquejada.vqr.service.ClientService;
import br.com.escritorioDeVaquejada.vqr.service.EventService;
import br.com.escritorioDeVaquejada.vqr.service.TicketService;
import br.com.escritorioDeVaquejada.vqr.vo.event.EventRequestVO;
import br.com.escritorioDeVaquejada.vqr.vo.event.EventResponseVO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;

@Service
public class EventServiceImplementation implements EventService {
    private final EventRepository eventRepository;
    private final ClientService clientService;
    private final TicketService ticketService;
    private final Mapper mapper;

    @Autowired
    public EventServiceImplementation(
            EventRepository eventRepository,
            ClientService clientService,
            TicketService ticketService,
            Mapper mapper) {
        this.eventRepository = eventRepository;
        this.clientService = clientService;
        this.ticketService = ticketService;
        this.mapper = mapper;
    }

    //todo verificar necessiade de adicionar um valor para isFinished na criação de um evento, não deixando-o como null no banco de dados
    @Transactional
    public EventResponseVO saveEvent(EventRequestVO newEvent, UUID clientId) {
        ClientModel owner = clientService.findEntityById(clientId);
        EventModel eventToBeSaved = mapper.parseObject(newEvent, EventModel.class);
        eventToBeSaved.setOwner(owner);
        eventToBeSaved.setDateTime(captureCurrentDateAndTime());
        EventModel eventCreated = eventRepository.save(eventToBeSaved);
        ticketService.saveEmptyTickets(eventCreated);
        return mapper.parseObject(eventCreated, EventResponseVO.class);
    }
    public Page<EventResponseVO> findEventsByClientIdAndNameContains(
            UUID clientId, String name, Pageable pageable){
        ClientModel owner = clientService.findEntityById(clientId);
        Page<EventModel> eventModelsPage =
                eventRepository.findByOwnerAndNameContainingIgnoreCase(owner, name, pageable);
        Page<EventResponseVO> eventResponsesPage =
                eventModelsPage.map(
                        eventModel -> mapper.parseObject(eventModel, EventResponseVO.class));
        return eventResponsesPage;
    }
    public EventResponseVO findEventByID(UUID eventID) throws ResourceNotFoundException{
        EventModel eventModel= eventRepository.findById(eventID).orElseThrow(()->
                new ResourceNotFoundException("Event not found!"));
        return mapper.parseObject(eventModel, EventResponseVO.class);
    }

    @Transactional
    public void deleteById(UUID id) {
        EventModel eventToBeDeleted = eventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Event not found!"));
        eventRepository.deleteById(id);
    }

    //todo verificar se o método captureCurrentDateAndTime não deveria ser público em uma classe de utils
    private LocalDateTime captureCurrentDateAndTime(){
        Instant now = Instant.now();
        ZoneId localZone = ZoneId.systemDefault();
        return now.atZone(localZone).toLocalDateTime();
    }
}
