package br.com.escritorioDeVaquejada.vqr.controllers;


import br.com.escritorioDeVaquejada.vqr.models.Client;
import br.com.escritorioDeVaquejada.vqr.services.ClientServices;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientServices clientServices;

    @PostMapping()
    public ResponseEntity<Client> saveClient(@RequestBody Client newClient){
        return new ResponseEntity<>(clientServices.saveClient(newClient), HttpStatus.CREATED);
    }
    @GetMapping()
    public ResponseEntity<List<Client>> findAll(){
        return new ResponseEntity<>(clientServices.findAll(), HttpStatus.OK);   
    }
    @GetMapping("/{id}")
    public ResponseEntity<Client> findById(@PathVariable(value = "id")UUID id){
        Optional<Client> client = clientServices.findById(id);
        return client.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}

