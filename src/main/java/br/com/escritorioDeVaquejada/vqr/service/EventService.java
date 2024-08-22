package br.com.escritorioDeVaquejada.vqr.service;


import br.com.escritorioDeVaquejada.vqr.vo.EventVO;

import java.util.List;
import java.util.UUID;

public interface EventService {
    EventVO saveEvent(EventVO newEvent, UUID clientId);
    List<EventVO> findEventsByClientId(UUID clientId);
    EventVO findEventByID(UUID eventId);
}
