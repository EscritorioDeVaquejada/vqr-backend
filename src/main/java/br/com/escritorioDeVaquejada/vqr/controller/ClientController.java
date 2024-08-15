package br.com.escritorioDeVaquejada.vqr.controller;

import br.com.escritorioDeVaquejada.vqr.exception.BadRequestException;
import br.com.escritorioDeVaquejada.vqr.service.ClientService;
import br.com.escritorioDeVaquejada.vqr.vo.ClientVo;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @PostMapping()
    public ResponseEntity<ClientVo> saveClient(
            @RequestBody @Valid ClientVo newClient,
            BindingResult errorsInRequest) throws BadRequestException{
        if(errorsInRequest.hasErrors()){
            throw new BadRequestException("Invalid data!");
        }
        return new ResponseEntity<>(clientService.saveClient(newClient), HttpStatus.CREATED);
    }
    @GetMapping()
    public ResponseEntity<List<ClientVo>> findAll(){
        return new ResponseEntity<>(clientService.findAll(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ClientVo> findById(
            @PathVariable(value = "id") UUID id){
        return new ResponseEntity<>(clientService.findById(id),HttpStatus.OK);
    }
}

