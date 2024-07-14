package br.com.escritorioDeVaquejada.vqr.repositories;

import br.com.escritorioDeVaquejada.vqr.models.ClientModel;
import br.com.escritorioDeVaquejada.vqr.models.EventModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface EventRepository extends JpaRepository<EventModel, UUID> {
    List<EventModel> findAllByOwnerOrderByDateTime(ClientModel Owner);
}
