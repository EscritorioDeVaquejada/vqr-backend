package br.com.escritorioDeVaquejada.vqr.controller;

import br.com.escritorioDeVaquejada.vqr.exception.BadRequestException;
import br.com.escritorioDeVaquejada.vqr.service.ClientService;
import br.com.escritorioDeVaquejada.vqr.vo.client.ClientRequestVO;
import br.com.escritorioDeVaquejada.vqr.vo.client.ClientResponseVO;
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
@RequestMapping("/api/clients/v1")
@Tag(name = "Clients", description = "Endpoint for managing clients")
public class ClientController {
    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
            summary = "Save a client",
            description = "Saves a new client in the database",
            tags = "Clients",
            responses = {
                    @ApiResponse(
                            description = "Created",
                            responseCode = "201",
                            content = @Content(schema = @Schema(implementation = ClientResponseVO.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
            })
    public ResponseEntity<ClientResponseVO> saveClient(
            @Valid @RequestBody ClientRequestVO newClient,
            BindingResult errorsInRequest) throws BadRequestException {
        if (errorsInRequest.hasErrors()) {
            throw new BadRequestException("Invalid client data!");
        }
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(clientService.saveClient(newClient));
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
            summary = "Get all clients",
            description = "Get all clients registered in the database",
            tags = "Clients",
            responses = {
                    @ApiResponse(description = "Ok", responseCode = "200", content = @Content(
                            array = @ArraySchema(schema = @Schema(implementation = ClientResponseVO.class))
                    )),
                    @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content),
                    @ApiResponse(description = "Internal Sever Error", responseCode = "500", content = @Content)
            })
    public ResponseEntity<List<ClientResponseVO>> findAll() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(clientService.findAll());
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
            summary = "Get a client by ID",
            description = "Returns a client based on their ID",
            tags = "Clients",
            responses = {
                    @ApiResponse(description = "Ok", responseCode = "200", content = @Content(
                            schema = @Schema(implementation = ClientResponseVO.class)
                    )),
                    @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
            })
    public ResponseEntity<ClientResponseVO> findById(
            @Parameter(
                    description = "ID of the client to be retrieved, must be a valid UUID format",
                    in = ParameterIn.PATH,
                    required = true)
            @PathVariable(value = "id")
            UUID id) {
        return new ResponseEntity<>(clientService.findById(id), HttpStatus.OK);
    }
}

