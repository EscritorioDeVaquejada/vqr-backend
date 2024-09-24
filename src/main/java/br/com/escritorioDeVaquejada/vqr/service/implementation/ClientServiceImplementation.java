package br.com.escritorioDeVaquejada.vqr.service.implementation;


import br.com.escritorioDeVaquejada.vqr.exception.ResourceNotFoundException;
import br.com.escritorioDeVaquejada.vqr.mapper.Mapper;
import br.com.escritorioDeVaquejada.vqr.model.ClientModel;
import br.com.escritorioDeVaquejada.vqr.repository.ClientRepository;
import br.com.escritorioDeVaquejada.vqr.repository.EventRepository;
import br.com.escritorioDeVaquejada.vqr.service.ClientService;
import br.com.escritorioDeVaquejada.vqr.vo.client.ClientDetailResponseVO;
import br.com.escritorioDeVaquejada.vqr.vo.client.ClientSaveVO;
import br.com.escritorioDeVaquejada.vqr.vo.client.ClientSummaryResponseVO;
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
    //todo evitando referência circular
    //temporário
    private final EventRepository eventRepository;
    private final Mapper mapper;

    @Autowired
    public ClientServiceImplementation(
            ClientRepository clientRepository,
            EventRepository eventRepository,
            Mapper mapper) {
        this.clientRepository = clientRepository;
        this.eventRepository = eventRepository;
        this.mapper = mapper;
    }

    public ClientDetailResponseVO saveClient(ClientSaveVO newClient) {
        return mapper.parseObject(clientRepository.save(mapper.parseObject(newClient, ClientModel.class)), ClientDetailResponseVO.class);
    }

    public Page<ClientDetailResponseVO> findClientDetailsByNameContainingIgnoreCase(
            String name, Pageable pageable) {
        Page<ClientModel> clientModelsPage =
                clientRepository.findByNameContainingIgnoreCase(name, pageable);
        return clientModelsPage.map(
                clientModel -> mapper.parseObject(clientModel, ClientDetailResponseVO.class));
    }

    @Transactional
    public Page<ClientSummaryResponseVO> findClientSummaryByNameContainingIgnoreCase(
            String name, Pageable pageable) {
        Page<ClientModel> clientModelsPage =
                clientRepository.findByNameContainingIgnoreCase(name, pageable);
        Page<ClientSummaryResponseVO> result = clientModelsPage.map(
                clientModel -> mapper.parseObject(clientModel, ClientSummaryResponseVO.class));
        //todo Refatorar para uma maneria mais simples
        for(int index=0; index<clientModelsPage.getNumberOfElements(); index++){
            result.getContent().get(index).setEventNumbers(eventRepository.findAllByOwner(
                    clientModelsPage.getContent().get(index)
            ).size());
        }
        return result;
    }

    public ClientDetailResponseVO findById(UUID id) throws ResourceNotFoundException {
        ClientModel client = clientRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Client not found!"));
        return mapper.parseObject(client, ClientDetailResponseVO.class);
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
    public ClientDetailResponseVO partialUpdates(UUID id, JsonNode patchNode)
            throws ResourceNotFoundException {
        ClientModel clientToBeUpdated = clientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found!"));
        try {
            objectMapper.readerForUpdating(clientToBeUpdated).readValue(patchNode);
        } catch (Exception e) {
            throw new RuntimeException("Unable to copy properties!");
        }

        return mapper.parseObject(clientRepository.save(clientToBeUpdated), ClientDetailResponseVO.class);
    }

     */


}
