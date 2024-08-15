package br.com.escritorioDeVaquejada.vqr.service;


import br.com.escritorioDeVaquejada.vqr.vo.EventVo;

import java.util.List;
import java.util.UUID;

public interface EventService {
    EventVo saveEvent(EventVo newEvent, UUID clientId);
    List<EventVo> findEventsByClientId(UUID clientId);
    EventVo findEventByID(UUID eventId);
}
