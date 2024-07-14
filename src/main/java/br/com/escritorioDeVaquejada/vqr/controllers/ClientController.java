package br.com.escritorioDeVaquejada.vqr.controllers;


import br.com.escritorioDeVaquejada.vqr.exceptions.ResourceNotFoundException;
import br.com.escritorioDeVaquejada.vqr.models.ClientModel;
import br.com.escritorioDeVaquejada.vqr.services.ClientServices;
import br.com.escritorioDeVaquejada.vqr.vo.ClientVo;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientServices clientServices;

    @PostMapping()
    public ResponseEntity<ClientModel> saveClient(@RequestBody @Valid ClientVo newClient){
        return new ResponseEntity<>(clientServices.saveClient(newClient), HttpStatus.CREATED);
    }
    @GetMapping()
    public ResponseEntity<List<ClientVo>> findAll(){
        return new ResponseEntity<>(clientServices.findAll(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ClientVo> findById(@PathVariable(value = "id")UUID id) throws ResourceNotFoundException {
        return new ResponseEntity<>(clientServices.findById(id),HttpStatus.OK);
    }
}

