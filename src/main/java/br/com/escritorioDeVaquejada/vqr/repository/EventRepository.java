package br.com.escritorioDeVaquejada.vqr.repository;

import br.com.escritorioDeVaquejada.vqr.model.ClientModel;
import br.com.escritorioDeVaquejada.vqr.model.EventModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface EventRepository extends JpaRepository<EventModel, UUID> {
    List<EventModel> findAllByOwnerOrderByDateTime(ClientModel Owner);

    Page<EventModel> findByOwnerAndNameContainingIgnoreCase(
            ClientModel clientModel, String name, Pageable pageable);
}
