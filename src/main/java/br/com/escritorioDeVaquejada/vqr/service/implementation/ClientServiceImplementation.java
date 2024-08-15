package br.com.escritorioDeVaquejada.vqr.service.implementation;


import br.com.escritorioDeVaquejada.vqr.exception.ResourceNotFoundException;
import br.com.escritorioDeVaquejada.vqr.mapper.Mapper;
import br.com.escritorioDeVaquejada.vqr.model.ClientModel;
import br.com.escritorioDeVaquejada.vqr.repository.ClientRepository;
import br.com.escritorioDeVaquejada.vqr.service.ClientService;
import br.com.escritorioDeVaquejada.vqr.vo.ClientVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ClientServiceImplementation implements ClientService {

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
