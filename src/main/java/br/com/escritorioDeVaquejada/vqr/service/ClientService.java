package br.com.escritorioDeVaquejada.vqr.service;

import br.com.escritorioDeVaquejada.vqr.model.ClientModel;
import br.com.escritorioDeVaquejada.vqr.vo.client.ClientRequestVO;
import br.com.escritorioDeVaquejada.vqr.vo.client.ClientResponseVO;

import java.util.List;
import java.util.UUID;
public interface ClientService {
    ClientResponseVO saveClient(ClientRequestVO newClient);
    List<ClientResponseVO> findAll();
    ClientResponseVO findById(UUID id);
    ClientModel findEntityById(UUID id);

}
