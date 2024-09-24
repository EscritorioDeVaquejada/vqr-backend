package br.com.escritorioDeVaquejada.vqr.service;

import br.com.escritorioDeVaquejada.vqr.model.EventModel;
import br.com.escritorioDeVaquejada.vqr.model.TicketModel;
import br.com.escritorioDeVaquejada.vqr.vo.ticket.TicketDetailResponseVO;
import br.com.escritorioDeVaquejada.vqr.vo.ticket.TicketRepresentationSummaryVO;
import br.com.escritorioDeVaquejada.vqr.vo.ticket.TicketStatusResponseVO;
import br.com.escritorioDeVaquejada.vqr.vo.ticket.TicketUpdateVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;


public interface TicketService {
    List<TicketModel> saveEmptyTickets(EventModel event);

    TicketDetailResponseVO findTicketDetailsById(UUID ticketId);
    Page<TicketStatusResponseVO> findTicketStatusByEventId(UUID eventId, Pageable pageable);
    Page<TicketRepresentationSummaryVO> findTicketRepresentationSummaryByEventId(UUID eventId, Pageable pageable);

    TicketDetailResponseVO updateByEventIdAndNumber(
            UUID eventId, Integer number, TicketUpdateVO update);
}