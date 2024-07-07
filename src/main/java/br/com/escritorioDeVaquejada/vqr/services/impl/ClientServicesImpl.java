package br.com.escritorioDeVaquejada.vqr.services.impl;


import br.com.escritorioDeVaquejada.vqr.models.Client;
import br.com.escritorioDeVaquejada.vqr.repositories.ClientRepository;
import br.com.escritorioDeVaquejada.vqr.services.ClientServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ClientServicesImpl implements ClientServices {

    @Autowired
    private ClientRepository clientRepository;

    public Client saveClient(Client newClient){
        return clientRepository.save(newClient);
    }

    public List<Client> findAll(){
        return clientRepository.findAll();
    }
    public Optional<Client> findById(UUID id) {
        return clientRepository.findById(id);
    }
}
