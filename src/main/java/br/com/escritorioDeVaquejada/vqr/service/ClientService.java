package br.com.escritorioDeVaquejada.vqr.service;

import br.com.escritorioDeVaquejada.vqr.model.ClientModel;
import br.com.escritorioDeVaquejada.vqr.vo.ClientVo;

import java.util.List;
import java.util.UUID;
public interface ClientService {
    ClientVo saveClient(ClientVo newClient);
    List<ClientVo> findAll();
    ClientVo findById(UUID id);
    ClientModel findEntityById(UUID id);

}
