package br.com.escritorioDeVaquejada.vqr.repository;

import br.com.escritorioDeVaquejada.vqr.model.TicketModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TicketRepository extends JpaRepository<TicketModel, UUID> {
}
