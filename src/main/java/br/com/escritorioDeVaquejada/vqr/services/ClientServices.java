package br.com.escritorioDeVaquejada.vqr.services;

import br.com.escritorioDeVaquejada.vqr.models.Client;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ClientServices {
    public Client saveClient(Client newClient);
    public List<Client> findAll();
    public Optional<Client> findById(UUID id);
}
