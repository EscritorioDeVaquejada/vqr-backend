package br.com.escritorioDeVaquejada.vqr.controllers;

import br.com.escritorioDeVaquejada.vqr.models.Event;
import br.com.escritorioDeVaquejada.vqr.services.EventServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/events")
public class EventController {
    @Autowired
    private EventServices eventServices;
    @PostMapping
    public ResponseEntity<Event> saveEvent(@RequestBody Event newEvent){
        return new ResponseEntity<>(eventServices.saveEvent(newEvent), HttpStatus.CREATED);
    }
}
