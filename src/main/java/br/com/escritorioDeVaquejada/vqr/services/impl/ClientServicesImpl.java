package br.com.escritorioDeVaquejada.vqr.services.impl;


import br.com.escritorioDeVaquejada.vqr.exceptions.ResourceNotFoundException;
import br.com.escritorioDeVaquejada.vqr.mappers.ModelMapper;
import br.com.escritorioDeVaquejada.vqr.models.Client;
import br.com.escritorioDeVaquejada.vqr.repositories.ClientRepository;
import br.com.escritorioDeVaquejada.vqr.services.ClientServices;
import br.com.escritorioDeVaquejada.vqr.vo.ClientVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    public List<ClientVo> findAll() throws RuntimeException{
        return ModelMapper.parseListObjects(clientRepository.findAll(),ClientVo.class);
    }
    public ClientVo findById(UUID id) {
        Optional<Client> client = clientRepository.findById(id);
        return client.map(value -> ModelMapper.parseObject(value,ClientVo.class)).orElseThrow(() -> new ResourceNotFoundException("FUDEU"));
    }
}
