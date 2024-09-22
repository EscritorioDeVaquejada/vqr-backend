package br.com.escritorioDeVaquejada.vqr.service;


import br.com.escritorioDeVaquejada.vqr.vo.event.EventRequestVO;
import br.com.escritorioDeVaquejada.vqr.vo.event.EventResponseVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface EventService {
    EventResponseVO saveEvent(EventRequestVO newEvent, UUID clientId);
    Page<EventResponseVO> findEventsByClientIdAndNameContains(
            UUID clientId, String name, Pageable pageable);
    EventResponseVO findEventByID(UUID eventId);
}
