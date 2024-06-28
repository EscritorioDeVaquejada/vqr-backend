package br.com.escritorioDeVaquejada.vqr.services.impl;


import br.com.escritorioDeVaquejada.vqr.models.Client;
import br.com.escritorioDeVaquejada.vqr.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientServicesImpl implements br.com.escritorioDeVaquejada.vqr.services.ClientServices {

    @Autowired
    private ClientRepository clientRepository;

    public Client saveClient(Client newClient){
        return clientRepository.save(newClient);
    }

}
