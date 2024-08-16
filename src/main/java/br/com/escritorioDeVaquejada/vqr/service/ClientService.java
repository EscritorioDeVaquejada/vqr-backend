package br.com.escritorioDeVaquejada.vqr.service;

import br.com.escritorioDeVaquejada.vqr.model.ClientModel;
import br.com.escritorioDeVaquejada.vqr.vo.ClientVO;

import java.util.List;
import java.util.UUID;
public interface ClientService {
    ClientVO saveClient(ClientVO newClient);
    List<ClientVO> findAll();
    ClientVO findById(UUID id);
    ClientModel findEntityById(UUID id);

}
