package br.com.escritorioDeVaquejada.vqr.services.impl;


import br.com.escritorioDeVaquejada.vqr.mappers.ModelMapper;
import br.com.escritorioDeVaquejada.vqr.models.Client;
import br.com.escritorioDeVaquejada.vqr.models.Event;
import br.com.escritorioDeVaquejada.vqr.repositories.EventRepository;
import br.com.escritorioDeVaquejada.vqr.services.ClientServices;
import br.com.escritorioDeVaquejada.vqr.services.EventServices;
import br.com.escritorioDeVaquejada.vqr.vo.ClientVo;
import br.com.escritorioDeVaquejada.vqr.vo.eventsVo.EventVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class EventServicesImpl implements EventServices {
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private ClientServices clientServices;
    @Override
    public EventVo saveEvent(EventVo newEvent, UUID clientId) {
        ClientVo owner = clientServices.findById(clientId);
        Event event = ModelMapper.parseObject(newEvent,Event.class);
        event.setOwner(ModelMapper.parseObject(owner,Client.class));
        eventRepository.save(event);
        return ModelMapper.parseObject(event, EventVo.class);
    }

}
