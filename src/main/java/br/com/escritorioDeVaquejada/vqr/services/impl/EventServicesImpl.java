package br.com.escritorioDeVaquejada.vqr.services.impl;


import br.com.escritorioDeVaquejada.vqr.mappers.ModelMapper;
import br.com.escritorioDeVaquejada.vqr.models.ClientModel;
import br.com.escritorioDeVaquejada.vqr.models.EventModel;
import br.com.escritorioDeVaquejada.vqr.repositories.EventRepository;
import br.com.escritorioDeVaquejada.vqr.services.ClientServices;
import br.com.escritorioDeVaquejada.vqr.services.EventServices;
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
public class EventServicesImpl implements EventServices {
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private ClientServices clientServices;

    @Transactional
    public EventVo saveEvent(EventVo newEvent, UUID clientId) {
        ClientModel owner = clientServices.findEntityById(clientId);
        EventModel event = ModelMapper.parseObject(newEvent, EventModel.class);
        event.setOwner(owner);
        Instant now = Instant.now();
        ZoneId localZone = ZoneId.systemDefault();
        LocalDateTime localDateTime = now.atZone(localZone).toLocalDateTime();
        event.setDateTime(localDateTime);
        return ( ModelMapper.parseObject(eventRepository.save(event), EventVo.class));
    }
    public List<EventVo> findEventsByClientId(UUID clientId){
        ClientModel owner = clientServices.findEntityById(clientId);
        List<EventModel> events = eventRepository.findAllByOwnerOrderByDateTime(owner);
        return ModelMapper.parseListObjects(events,EventVo.class);
    }
}
