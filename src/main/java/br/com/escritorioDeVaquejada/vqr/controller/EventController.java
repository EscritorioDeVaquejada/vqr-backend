package br.com.escritorioDeVaquejada.vqr.controller;

import br.com.escritorioDeVaquejada.vqr.exception.BadRequestException;
import br.com.escritorioDeVaquejada.vqr.representation.PagedEventResponse;
import br.com.escritorioDeVaquejada.vqr.service.EventService;
import br.com.escritorioDeVaquejada.vqr.vo.event.EventRequestVO;
import br.com.escritorioDeVaquejada.vqr.vo.event.EventResponseVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/events")
@Tag(name = "Events", description = "Endpoint for managing events.")
public class EventController {
    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
            summary = "Save a event",
            description = "Saves a new event in the database.",
            tags = "Events",
            responses = {
                    @ApiResponse(
                            description = "Created",
                            responseCode = "204",
                            content = @Content(
                                    schema = @Schema(
                                            implementation = EventResponseVO.class))
                    ),
                    @ApiResponse(
                            description = "Bad Request",
                            responseCode = "400",
                            content = @Content
                    ),
                    @ApiResponse(
                            description = "Forbidden",
                            responseCode = "403",
                            content = @Content
                    ),
                    @ApiResponse(
                            description = "Internal Sever Error",
                            responseCode = "500",
                            content = @Content
                    )
            }
    )
    public ResponseEntity<EventResponseVO> saveEvent(
            @RequestBody @Valid
            EventRequestVO newEvent,
            @Parameter(
                    name = "clientId",
                    in = ParameterIn.QUERY,
                    required = true,
                    description = "Unique identifier for the client associated with the event" +
                            ". Must be a valid UUID format."
            )
            @RequestParam(value = "clientId")
            UUID clientId,
            BindingResult errorsInRequest) throws BadRequestException
    {
        if (errorsInRequest.hasErrors()) {
            throw new BadRequestException("Invalid data!");
        }
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(eventService.saveEvent(newEvent, clientId));
    }

    @Operation(
            summary = "Get events by client ID and by name containing specified text",
            description = "Retrieves a paginated list of events associated with a specific client. " +
                    "Events can be filtered by names containing the specified text in a " +
                    "case-insensitive manner. If no name is provided, the method returns all " +
                    "events related to the client.",
            tags = "Events",
            responses = {
                    @ApiResponse(
                            description = "Ok",
                            responseCode = "200",
                            content = @Content(
                                    schema = @Schema(
                                            implementation = PagedEventResponse.class)
                            )
                    ),
                    @ApiResponse(
                            description = "Forbidden",
                            responseCode = "403",
                            content = @Content
                    ),
                    @ApiResponse(
                            description = "Internal Sever Error",
                            responseCode = "500",
                            content = @Content
                    )
            }
    )
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<EventResponseVO>> findEventsByClientIdAndNameContains(
            @Parameter(
                    name = "page",
                    required = false,
                    in = ParameterIn.QUERY,
                    description = "The page number to retrieve (zero-based index)."
            )
            @RequestParam(value = "page", defaultValue = "0")
            Integer page,
            @Parameter(
                    name = "size",
                    required = false,
                    in = ParameterIn.QUERY,
                    description = "The number of items per page."
            )
            @RequestParam(value = "size", defaultValue = "12")
            Integer size,
            @Parameter(
                    name = "direction",
                    required = false,
                    in = ParameterIn.QUERY,
                    description = "The sorting direction for the results. Use 'asc' for " +
                            "ascending and 'desc' for descending."
            )
            @RequestParam(value = "direction", defaultValue = "asc")
            String direction,
            @Parameter(
                    name = "name",
                    required = false,
                    in = ParameterIn.QUERY,
                    description = "The text to search for in event names. " +
                            "Only events whose names contain this text will be returned."
            )
            @RequestParam(value = "name", defaultValue = "")
            String name,
            @Parameter(
                    name = "clientId",
                    in = ParameterIn.QUERY,
                    required = true,
                    description = "Unique identifier for the client associated with the event" +
                            ". Must be a valid UUID format."
            )
            @RequestParam(value = "clientId")
            UUID clientId)
    {
        Sort.Direction sortDirection =
                "desc".equalsIgnoreCase(direction) ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, "name"));
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(eventService.findEventsByClientIdAndNameContains(clientId, name, pageable));
    }
}
