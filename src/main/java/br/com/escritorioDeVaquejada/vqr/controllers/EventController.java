package br.com.escritorioDeVaquejada.vqr.controllers;

import br.com.escritorioDeVaquejada.vqr.services.EventServices;
import br.com.escritorioDeVaquejada.vqr.vo.eventsVo.EventVo;
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
    @PostMapping("/{clientId}")
    public ResponseEntity<EventVo> saveEvent(@RequestBody EventVo newEvent, @PathVariable(value = "clientId")UUID clientId){
        return new ResponseEntity<>(eventServices.saveEvent(newEvent,clientId), HttpStatus.CREATED);
    }
  /*@GetMapping
   public  ResponseEntity<List<EventVo>> findAll(){

   }*/
}
