package br.com.escritorioDeVaquejada.vqr.service;

import br.com.escritorioDeVaquejada.vqr.model.ClientModel;
import br.com.escritorioDeVaquejada.vqr.vo.client.ClientPatchVO;
import br.com.escritorioDeVaquejada.vqr.vo.client.ClientResponseVO;
import br.com.escritorioDeVaquejada.vqr.vo.client.ClientSaveVO;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;
import java.util.UUID;

public interface ClientService {
    ClientResponseVO saveClient(ClientSaveVO newClient);

    Page<ClientResponseVO> findClientsByNameContainingIgnoreCase(String name, Pageable pageable);

    ClientResponseVO findById(UUID id);

    ClientModel findEntityById(UUID id);

    void deleteById(UUID id);

    /*
    ClientResponseVO partialUpdates(UUID id, JsonNode patchNode);

     */
}

