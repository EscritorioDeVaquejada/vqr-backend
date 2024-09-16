package br.com.escritorioDeVaquejada.vqr.repository;

import br.com.escritorioDeVaquejada.vqr.model.ClientModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;
public interface ClientRepository extends JpaRepository<ClientModel, UUID> {
    Page<ClientModel> findByNameContainingIgnoreCase(String name, Pageable pageable);
}