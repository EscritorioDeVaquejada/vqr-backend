package br.com.escritorioDeVaquejada.vqr.controllers;

import br.com.escritorioDeVaquejada.vqr.services.EventServices;
import br.com.escritorioDeVaquejada.vqr.vo.EventVo;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/events")
public class EventController {
    @Autowired
    private EventServices eventServices;
    @PostMapping()
    public ResponseEntity<EventVo> saveEvent(
            @RequestBody @Valid EventVo newEvent,
            @RequestParam(value = "clientId")UUID clientId){
        return new ResponseEntity<>(eventServices.saveEvent(newEvent,clientId), HttpStatus.CREATED);
    }
  @GetMapping
   public  ResponseEntity<List<EventVo>> findEventsByClientId(
           @RequestParam(value="clientId")UUID clientId){
        return new ResponseEntity<>(eventServices.findEventsByClientId(clientId),HttpStatus.OK);
   }
}
