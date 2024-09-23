package br.com.escritorioDeVaquejada.vqr.controller;

import br.com.escritorioDeVaquejada.vqr.service.TicketService;
import br.com.escritorioDeVaquejada.vqr.vo.ticket.TicketDetailResponseVO;
import br.com.escritorioDeVaquejada.vqr.vo.ticket.TicketRepresentationSummaryVO;
import br.com.escritorioDeVaquejada.vqr.vo.ticket.TicketStatusResponseVO;
import io.swagger.v3.oas.annotations.Parameter;
import org.springdoc.core.converters.models.PageableAsQueryParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {
    private final TicketService ticketService;

    @Autowired
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<TicketDetailResponseVO> findTicketDetailsById(
            @PathVariable(value = "id") UUID id){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ticketService.findTicketDetailsById(id));
    }

    @PageableAsQueryParam
    @GetMapping("/status")
    public ResponseEntity<Page<TicketStatusResponseVO>> findTicketStatusByEventId(
            @Parameter(hidden = true)
            @PageableDefault(
                    size = 20,
                    page = 0,
                    direction = Sort.Direction.ASC,
                    sort = {"number"}
            )
            Pageable pageable,
            @RequestParam(value = "eventId")
            UUID eventId){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ticketService.findTicketStatusByEventId(eventId, pageable));
    }

    @PageableAsQueryParam
    @GetMapping("/representation-summary")
    public ResponseEntity<Page<TicketRepresentationSummaryVO>> findTicketRepresentationSummaryByEventId(
            @Parameter(hidden = true)
            @PageableDefault(
                    page = 0,
                    direction = Sort.Direction.ASC,
                    sort = {"number"},
                    size = 20
            )
            Pageable pageable,
            @RequestParam(value = "eventId") UUID eventId)
    {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ticketService.findTicketRepresentationSummaryByEventId(
                        eventId,
                        pageable
                )
        );
    }

}
