package br.com.escritorioDeVaquejada.vqr.controllers;


import br.com.escritorioDeVaquejada.vqr.models.Client;
import br.com.escritorioDeVaquejada.vqr.services.ClientServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private ClientServices clientServices;

    @PostMapping()
    public ResponseEntity<Client> save(@RequestBody Client newClient){

        return new ResponseEntity<>(clientServices.saveClient(newClient), HttpStatus.CREATED);
    }

}
