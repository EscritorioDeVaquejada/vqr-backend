package br.com.escritorioDeVaquejada.vqr.repositories;

import br.com.escritorioDeVaquejada.vqr.models.TicketModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TicketRepository extends JpaRepository<TicketModel, UUID> {
}
