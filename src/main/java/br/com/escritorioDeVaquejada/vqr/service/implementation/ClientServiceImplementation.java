package br.com.escritorioDeVaquejada.vqr.service.implementation;


import br.com.escritorioDeVaquejada.vqr.exception.ResourceNotFoundException;
import br.com.escritorioDeVaquejada.vqr.mapper.Mapper;
import br.com.escritorioDeVaquejada.vqr.model.ClientModel;
import br.com.escritorioDeVaquejada.vqr.repository.ClientRepository;
import br.com.escritorioDeVaquejada.vqr.service.ClientService;
import br.com.escritorioDeVaquejada.vqr.vo.ClientVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ClientServiceImplementation implements ClientService {
    private final ClientRepository clientRepository;
    private final Mapper mapper;

    @Autowired
    public ClientServiceImplementation(ClientRepository clientRepository, Mapper mapper) {
        this.clientRepository = clientRepository;
        this.mapper = mapper;
    }

    public ClientVO saveClient(ClientVO newClient){
        return mapper.parseObject(clientRepository.save(mapper.parseObject(newClient, ClientModel.class)), ClientVO.class);
    }

    public List<ClientVO> findAll(){
        return mapper.parseListObjects(clientRepository.findAll(), ClientVO.class);
    }

    public ClientVO findById(UUID id) throws ResourceNotFoundException{
        ClientModel client = clientRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Client not found!"));
        return mapper.parseObject(client, ClientVO.class);
    }
    public ClientModel findEntityById(UUID id) throws ResourceNotFoundException{
        return clientRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Client not found!"));
    }
}
