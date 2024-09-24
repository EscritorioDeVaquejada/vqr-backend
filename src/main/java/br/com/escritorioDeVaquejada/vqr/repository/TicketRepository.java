package br.com.escritorioDeVaquejada.vqr.repository;

import br.com.escritorioDeVaquejada.vqr.model.EventModel;
import br.com.escritorioDeVaquejada.vqr.model.TicketModel;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface TicketRepository extends JpaRepository<TicketModel, UUID> {
    Page<TicketModel> findByEvent(EventModel event, Pageable pageable);
    Optional<TicketModel> findByNumber(Integer number);
}
