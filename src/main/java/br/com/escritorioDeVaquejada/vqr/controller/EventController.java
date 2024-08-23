package br.com.escritorioDeVaquejada.vqr.controller;

import br.com.escritorioDeVaquejada.vqr.exception.BadRequestException;
import br.com.escritorioDeVaquejada.vqr.service.EventService;
import br.com.escritorioDeVaquejada.vqr.vo.event.EventVO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/events/v1")
public class EventController {
    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EventVO> saveEvent(
            @RequestBody @Valid EventVO newEvent,
            @RequestParam(value = "clientId") UUID clientId,
            BindingResult errorsInRequest) throws BadRequestException {
        if(errorsInRequest.hasErrors()){
            throw new BadRequestException("Invalid data!");
        }
        return new ResponseEntity<>(eventService.saveEvent(newEvent,clientId), HttpStatus.CREATED);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<EventVO>> findEventsByClientId(
            @RequestParam(value="clientId")UUID clientId){
        return new ResponseEntity<>(eventService.findEventsByClientId(clientId),HttpStatus.OK);
    }
}
