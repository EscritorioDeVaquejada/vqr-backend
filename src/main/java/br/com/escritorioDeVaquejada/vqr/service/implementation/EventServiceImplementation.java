package br.com.escritorioDeVaquejada.vqr.service.implementation;


import br.com.escritorioDeVaquejada.vqr.exception.ResourceNotFoundException;
import br.com.escritorioDeVaquejada.vqr.mapper.Mapper;
import br.com.escritorioDeVaquejada.vqr.model.ClientModel;
import br.com.escritorioDeVaquejada.vqr.model.EventModel;
import br.com.escritorioDeVaquejada.vqr.repository.EventRepository;
import br.com.escritorioDeVaquejada.vqr.service.ClientService;
import br.com.escritorioDeVaquejada.vqr.service.EventService;
import br.com.escritorioDeVaquejada.vqr.service.TicketService;
import br.com.escritorioDeVaquejada.vqr.vo.EventVo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.UUID;

@Service
public class EventServiceImplementation implements EventService {
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private ClientService clientService;
    @Autowired
    private TicketService ticketService;
    @Autowired
    private Mapper mapper;

    //todo verificar necessiade de adicionar um valor para isFinished na criação de um evento, para não deixá-lo como null no banco de dados
    @Transactional
    public EventVo saveEvent(EventVo newEvent, UUID clientId) {
        ClientModel owner = clientService.findEntityById(clientId);
        EventModel eventToBeSaved = mapper.parseObject(newEvent, EventModel.class);
        eventToBeSaved.setOwner(owner);
        eventToBeSaved.setDateTime(captureCurrentDateAndTime());
        EventModel eventCreated = eventRepository.save(eventToBeSaved);
        ticketService.saveEmptyTickets(eventCreated);
        return mapper.parseObject(eventCreated, EventVo.class);
    }
    public List<EventVo> findEventsByClientId(UUID clientId){
        ClientModel owner = clientService.findEntityById(clientId);
        List<EventModel> events = eventRepository.findAllByOwnerOrderByDateTime(owner);
        return mapper.parseListObjects(events,EventVo.class);
    }
    public EventVo findEventByID(UUID eventID) throws ResourceNotFoundException{
        EventModel eventModel= eventRepository.findById(eventID).orElseThrow(()-> new ResourceNotFoundException("FUDEU"));
        return mapper.parseObject(eventModel,EventVo.class);
    }
    //todo verificar se o método captureCurrentDateAndTime não deveria ser público em uuma classe de utils
    private LocalDateTime captureCurrentDateAndTime(){
        Instant now = Instant.now();
        ZoneId localZone = ZoneId.systemDefault();
        return now.atZone(localZone).toLocalDateTime();
    }
}
