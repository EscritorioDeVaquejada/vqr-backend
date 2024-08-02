package br.com.escritorioDeVaquejada.vqr.services.impl;


import br.com.escritorioDeVaquejada.vqr.exceptions.ResourceNotFoundException;
import br.com.escritorioDeVaquejada.vqr.mappers.ModelMapper;
import br.com.escritorioDeVaquejada.vqr.mappers.ModelMapperInterface;
import br.com.escritorioDeVaquejada.vqr.models.ClientModel;
import br.com.escritorioDeVaquejada.vqr.models.EventModel;
import br.com.escritorioDeVaquejada.vqr.repositories.EventRepository;
import br.com.escritorioDeVaquejada.vqr.services.ClientServices;
import br.com.escritorioDeVaquejada.vqr.services.EventServices;
import br.com.escritorioDeVaquejada.vqr.services.TicketServices;
import br.com.escritorioDeVaquejada.vqr.vo.EventVo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EventServicesImpl implements EventServices {
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private ClientServices clientServices;
    @Autowired
    private TicketServices ticketServices;
    @Autowired
    private ModelMapperInterface modelMapperInterface;
    @Transactional
    public EventVo saveEvent(EventVo newEvent, UUID clientId) {
        ClientModel owner = clientServices.findEntityById(clientId);
        EventModel event = modelMapperInterface.parseObject(newEvent, EventModel.class);
        event.setOwner(owner);
        event.setDateTime(captureCurrentDateAndTime());
        EventModel eventCreated = eventRepository.save(event);
        ticketServices.saveEmptyTickets(eventCreated);
        return ( modelMapperInterface.parseObject(eventCreated, EventVo.class));
    }
    public List<EventVo> findEventsByClientId(UUID clientId){
        ClientModel owner = clientServices.findEntityById(clientId);
        List<EventModel> events = eventRepository.findAllByOwnerOrderByDateTime(owner);
        return modelMapperInterface.parseListObjects(events,EventVo.class);
    }
    public EventVo findEventByID(UUID eventID){
        EventModel eventModel= eventRepository.findById(eventID).orElseThrow(()->new ResourceNotFoundException("FUDEU"));
        return modelMapperInterface.parseObject(eventModel,EventVo.class);
    }
    private LocalDateTime captureCurrentDateAndTime(){
        Instant now = Instant.now();
        ZoneId localZone = ZoneId.systemDefault();
        return now.atZone(localZone).toLocalDateTime();
    }
}
