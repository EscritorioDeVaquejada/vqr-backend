package br.com.escritorioDeVaquejada.vqr.services;


import br.com.escritorioDeVaquejada.vqr.vo.eventsVo.EventVo;

import java.util.UUID;

public interface EventServices {
    public EventVo saveEvent(EventVo newEvent, UUID clientId);
}
