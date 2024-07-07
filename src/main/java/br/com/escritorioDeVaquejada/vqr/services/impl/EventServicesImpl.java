package br.com.escritorioDeVaquejada.vqr.services.impl;


import br.com.escritorioDeVaquejada.vqr.models.Event;
import br.com.escritorioDeVaquejada.vqr.repositories.EventRepository;
import br.com.escritorioDeVaquejada.vqr.services.EventServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventServicesImpl implements EventServices {
    @Autowired
    private EventRepository eventRepository;

    @Override
    public Event saveEvent(Event newEvent) {return eventRepository.save(newEvent);}

}
