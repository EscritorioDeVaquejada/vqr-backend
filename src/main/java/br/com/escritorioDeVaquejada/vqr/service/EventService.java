package br.com.escritorioDeVaquejada.vqr.service;


import br.com.escritorioDeVaquejada.vqr.vo.event.EventRequestVO;
import br.com.escritorioDeVaquejada.vqr.vo.event.EventResponseVO;

import java.util.List;
import java.util.UUID;

public interface EventService {
    EventResponseVO saveEvent(EventRequestVO newEvent, UUID clientId);
    List<EventResponseVO> findEventsByClientId(UUID clientId);
    EventResponseVO findEventByID(UUID eventId);
}
