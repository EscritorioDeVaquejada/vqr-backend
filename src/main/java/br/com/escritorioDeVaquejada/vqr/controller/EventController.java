package br.com.escritorioDeVaquejada.vqr.controller;

import br.com.escritorioDeVaquejada.vqr.exception.BadRequestException;
import br.com.escritorioDeVaquejada.vqr.service.EventService;
import br.com.escritorioDeVaquejada.vqr.vo.EventVo;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/events")
public class EventController {
    @Autowired
    private EventService eventService;
    @PostMapping()
    public ResponseEntity<EventVo> saveEvent(
            @RequestBody @Valid EventVo newEvent,
            @RequestParam(value = "clientId") UUID clientId,
            BindingResult errorsInRequest) throws BadRequestException {
        if(errorsInRequest.hasErrors()){
            throw new BadRequestException("Invalid data!");
        }
        return new ResponseEntity<>(eventService.saveEvent(newEvent,clientId), HttpStatus.CREATED);
    }
  @GetMapping
   public ResponseEntity<List<EventVo>> findEventsByClientId(
           @RequestParam(value="clientId")UUID clientId){
        return new ResponseEntity<>(eventService.findEventsByClientId(clientId),HttpStatus.OK);
   }
}
