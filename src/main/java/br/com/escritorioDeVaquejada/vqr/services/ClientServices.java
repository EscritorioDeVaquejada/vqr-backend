package br.com.escritorioDeVaquejada.vqr.services;

import br.com.escritorioDeVaquejada.vqr.models.ClientModel;
import br.com.escritorioDeVaquejada.vqr.vo.ClientVo;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
public interface ClientServices {
    ClientVo saveClient(ClientVo newClient);
    List<ClientVo> findAll();
    ClientVo findById(UUID id);
    ClientModel findEntityById(UUID id);

}
