package br.com.escritorioDeVaquejada.vqr.controller;

import br.com.escritorioDeVaquejada.vqr.exception.BadRequestException;
import br.com.escritorioDeVaquejada.vqr.service.EventService;
import br.com.escritorioDeVaquejada.vqr.vo.event.EventRequestVO;
import br.com.escritorioDeVaquejada.vqr.vo.event.EventResponseVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/events/v1")
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
                                    schema = @Schema(implementation = EventResponseVO.class))),
                    @ApiResponse(description = "Bad Request", responseCode = "400",
                            content = @Content),
                    @ApiResponse(description = "Forbidden", responseCode = "403",
                            content = @Content),
                    @ApiResponse(description = "Internal Sever Error", responseCode = "500",
                            content = @Content)
            })
    public ResponseEntity<EventResponseVO> saveEvent(
            @RequestBody @Valid EventRequestVO newEvent,
            @Parameter(
                    name = "clientId",
                    in = ParameterIn.QUERY,
                    required = true,
                    description = "Unique identifier for the client associated with the event" +
                            ". Must be a valid UUID format.")
            @RequestParam(value = "clientId") UUID clientId,
            BindingResult errorsInRequest) throws BadRequestException {
        if (errorsInRequest.hasErrors()) {
            throw new BadRequestException("Invalid data!");
        }
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(eventService.saveEvent(newEvent, clientId));
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
            summary = "Get events by client ID",
            description = "Returns a list of events associated with a specific client.",
            tags = "Events",
            responses = {
                    @ApiResponse(
                            description = "Ok",
                            responseCode = "200",
                            content = @Content(
                                    array = @ArraySchema(
                                            schema = @Schema(
                                                    implementation = EventResponseVO.class)))),
                    @ApiResponse(description = "Forbidden", responseCode = "403",
                            content = @Content),
                    @ApiResponse(description = "Internal Sever Error", responseCode = "500",
                            content = @Content)
            }
    )
    public ResponseEntity<List<EventResponseVO>> findEventsByClientId(
            @Parameter(
                    name = "clientId",
                    in = ParameterIn.QUERY,
                    required = true,
                    description = "Unique identifier for the client associated with the event" +
                            ". Must be a valid UUID format.")
            @RequestParam(value = "clientId") UUID clientId) {
        return new ResponseEntity<>(eventService.findEventsByClientId(clientId), HttpStatus.OK);
    }
}
