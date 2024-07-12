package br.com.escritorioDeVaquejada.vqr.services;


import br.com.escritorioDeVaquejada.vqr.vo.EventVo;

import java.util.List;
import java.util.UUID;

public interface EventServices {
    public EventVo saveEvent(EventVo newEvent, UUID clientId);
    public List<EventVo> findEventsByClientId(UUID clientId);
}
