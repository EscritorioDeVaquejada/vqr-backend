package br.com.escritorioDeVaquejada.vqr.service;

import br.com.escritorioDeVaquejada.vqr.model.ClientModel;
import br.com.escritorioDeVaquejada.vqr.vo.client.ClientDetailResponseVO;
import br.com.escritorioDeVaquejada.vqr.vo.client.ClientSaveVO;
import br.com.escritorioDeVaquejada.vqr.vo.client.ClientSummaryResponseVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface ClientService {
    ClientDetailResponseVO saveClient(ClientSaveVO newClient);

    ClientDetailResponseVO findById(UUID id);

    ClientModel findEntityById(UUID id);

    void deleteById(UUID id);

    Page<ClientSummaryResponseVO> findClientSummaryByNameContainingIgnoreCase(String name, Pageable pageable);

    Page<ClientDetailResponseVO> findClientDetailsByNameContainingIgnoreCase(String name, Pageable pageable);

    /*
    ClientDetailResponseVO partialUpdates(UUID id, JsonNode patchNode);

     */
}

