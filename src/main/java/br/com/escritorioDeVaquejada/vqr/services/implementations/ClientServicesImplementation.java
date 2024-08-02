package br.com.escritorioDeVaquejada.vqr.services.implementations;


import br.com.escritorioDeVaquejada.vqr.exceptions.ResourceNotFoundException;
import br.com.escritorioDeVaquejada.vqr.mappers.Mapper;
import br.com.escritorioDeVaquejada.vqr.models.ClientModel;
import br.com.escritorioDeVaquejada.vqr.repositories.ClientRepository;
import br.com.escritorioDeVaquejada.vqr.services.ClientServices;
import br.com.escritorioDeVaquejada.vqr.vo.ClientVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ClientServicesImplementation implements ClientServices {

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private Mapper mapper;

    public ClientVo saveClient(ClientVo newClient){
        return mapper.parseObject(clientRepository.save(mapper.parseObject(newClient, ClientModel.class)),ClientVo.class);
    }

    public List<ClientVo> findAll(){
        return mapper.parseListObjects(clientRepository.findAll(),ClientVo.class);
    }

    public ClientVo findById(UUID id) throws ResourceNotFoundException{
        ClientModel client = clientRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("FUDEU"));
        return mapper.parseObject(client,ClientVo.class);
    }
    public ClientModel findEntityById(UUID id) throws ResourceNotFoundException{
        return clientRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("FUDEU"));
    }
}
