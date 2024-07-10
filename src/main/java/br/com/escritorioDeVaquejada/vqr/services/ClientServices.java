package br.com.escritorioDeVaquejada.vqr.services;

import br.com.escritorioDeVaquejada.vqr.models.Client;
import br.com.escritorioDeVaquejada.vqr.vo.ClientVo;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ClientServices {
    public Client saveClient(ClientVo newClient);
    public List<ClientVo> findAll();
    public ClientVo findById(UUID id);
}
