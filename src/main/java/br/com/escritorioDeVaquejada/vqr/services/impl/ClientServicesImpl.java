package br.com.escritorioDeVaquejada.vqr.services.impl;


import br.com.escritorioDeVaquejada.vqr.exceptions.ResourceNotFoundException;
import br.com.escritorioDeVaquejada.vqr.mappers.ModelMapper;
import br.com.escritorioDeVaquejada.vqr.models.ClientModel;
import br.com.escritorioDeVaquejada.vqr.repositories.ClientRepository;
import br.com.escritorioDeVaquejada.vqr.services.ClientServices;
import br.com.escritorioDeVaquejada.vqr.vo.ClientVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ClientServicesImpl implements ClientServices {

    @Autowired
    private ClientRepository clientRepository;

    public ClientModel saveClient(ClientVo newClient){
        return clientRepository.save(ModelMapper.parseObject(newClient, ClientModel.class));
    }

    public List<ClientVo> findAll() throws RuntimeException{
        return ModelMapper.parseListObjects(clientRepository.findAll(),ClientVo.class);
    }
    public ClientVo findById(UUID id) {
        ClientModel client = clientRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("FUDEU"));
        return ModelMapper.parseObject(client,ClientVo.class);
    }
    public ClientModel findEntityById(UUID id){
        return clientRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("FUDEU"));
    }
}
