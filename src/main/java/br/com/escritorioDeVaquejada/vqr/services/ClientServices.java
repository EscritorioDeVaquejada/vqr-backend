package br.com.escritorioDeVaquejada.vqr.services;

import br.com.escritorioDeVaquejada.vqr.models.ClientModel;
import br.com.escritorioDeVaquejada.vqr.vo.ClientVo;

import java.util.List;
import java.util.UUID;

public interface ClientServices {
    ClientModel saveClient(ClientVo newClient);
    List<ClientVo> findAll();
    ClientVo findById(UUID id);
    ClientModel findEntityById(UUID id);

}
