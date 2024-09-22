package br.com.escritorioDeVaquejada.vqr.service;

import br.com.escritorioDeVaquejada.vqr.model.ClientModel;
import br.com.escritorioDeVaquejada.vqr.vo.client.ClientResponseVO;
import br.com.escritorioDeVaquejada.vqr.vo.client.ClientSaveVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface ClientService {
    ClientResponseVO saveClient(ClientSaveVO newClient);

    Page<ClientResponseVO> findClientsByNameContainingIgnoreCase(String name, Pageable pageable);

    ClientResponseVO findById(UUID id);

    ClientModel findEntityById(UUID id);

}
