package br.com.escritorioDeVaquejada.vqr.service.implementation;


import br.com.escritorioDeVaquejada.vqr.exception.ResourceNotFoundException;
import br.com.escritorioDeVaquejada.vqr.mapper.Mapper;
import br.com.escritorioDeVaquejada.vqr.model.ClientModel;
import br.com.escritorioDeVaquejada.vqr.repository.ClientRepository;
import br.com.escritorioDeVaquejada.vqr.service.ClientService;
import br.com.escritorioDeVaquejada.vqr.vo.client.ClientPatchVO;
import br.com.escritorioDeVaquejada.vqr.vo.client.ClientResponseVO;
import br.com.escritorioDeVaquejada.vqr.vo.client.ClientSaveVO;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ClientServiceImplementation implements ClientService {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final ClientRepository clientRepository;
    private final Mapper mapper;

    @Autowired
    public ClientServiceImplementation(ClientRepository clientRepository, Mapper mapper) {
        this.clientRepository = clientRepository;
        this.mapper = mapper;
    }

    public ClientResponseVO saveClient(ClientSaveVO newClient) {
        return mapper.parseObject(clientRepository.save(mapper.parseObject(newClient, ClientModel.class)), ClientResponseVO.class);
    }

    public Page<ClientResponseVO> findClientsByNameContainingIgnoreCase(String name, Pageable pageable) {
        Page<ClientModel> clientModelsPage =
                clientRepository.findByNameContainingIgnoreCase(name, pageable);
        return clientModelsPage.map(
                clientModel -> mapper.parseObject(clientModel, ClientResponseVO.class));
    }

    public ClientResponseVO findById(UUID id) throws ResourceNotFoundException {
        ClientModel client = clientRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Client not found!"));
        return mapper.parseObject(client, ClientResponseVO.class);
    }

    public ClientModel findEntityById(UUID id) throws ResourceNotFoundException {
        return clientRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Client not found!"));
    }

    @Override
    @Transactional
    public void deleteById(UUID id) {
        ClientModel clientToBeDeleted = clientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found!"));
        clientRepository.delete(clientToBeDeleted);
    }

    /*
    @Transactional
    public ClientResponseVO partialUpdates(UUID id, JsonNode patchNode)
            throws ResourceNotFoundException {
        ClientModel clientToBeUpdated = clientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found!"));
        try {
            objectMapper.readerForUpdating(clientToBeUpdated).readValue(patchNode);
        } catch (Exception e) {
            throw new RuntimeException("Unable to copy properties!");
        }

        return mapper.parseObject(clientRepository.save(clientToBeUpdated), ClientResponseVO.class);
    }

     */


}
