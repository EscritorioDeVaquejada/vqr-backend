package br.com.escritorioDeVaquejada.vqr.controller;

import br.com.escritorioDeVaquejada.vqr.exception.BadRequestException;
import br.com.escritorioDeVaquejada.vqr.representation.PagedClientResponse;
import br.com.escritorioDeVaquejada.vqr.service.ClientService;
import br.com.escritorioDeVaquejada.vqr.vo.client.ClientResponseVO;
import br.com.escritorioDeVaquejada.vqr.vo.client.ClientSaveVO;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springdoc.core.converters.models.PageableAsQueryParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Tag(name = "Clients", description = "Endpoint for managing clients.")
@RestController
@RequestMapping("/api/clients")
public class ClientController {
    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @Operation(
            summary = "Save a client",
            description = "Saves a new client in the database.",
            tags = "Clients",
            responses = {
                    @ApiResponse(
                            description = "Created",
                            responseCode = "201",
                            content = @Content(
                                    schema = @Schema(
                                            implementation = ClientResponseVO.class))
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
                            description = "Internal Server Error",
                            responseCode = "500",
                            content = @Content
                    )
            }
    )
    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ClientResponseVO> saveClient(
            @Valid @RequestBody ClientSaveVO newClient,
            BindingResult errorsInRequest) throws BadRequestException {
        if (errorsInRequest.hasErrors()) {
            throw new BadRequestException("Invalid client data!");
        }
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(clientService.saveClient(newClient));
    }

    @Operation(
            summary = "Get clients by name containing specified text",
            description = "Retrieves a paginated list of clients. Clients can be filtered by names " +
                    "that contain the specified text, with the search being case-insensitive. " +
                    "If no name is provided, all clients will be returned while adhering " +
                    "to the pagination constraints.",
            tags = "Clients",
            responses = {
                    @ApiResponse(
                            description = "Ok",
                            responseCode = "200",
                            content = @Content(
                                    schema = @Schema(
                                            implementation = PagedClientResponse.class)
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
    @PageableAsQueryParam
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<ClientResponseVO>> findClientsByNameContainingIgnoreCase(
            @Parameter(hidden = true)
            @PageableDefault(size = 20, page = 0, sort = {"name"}, direction = Sort.Direction.ASC)
            Pageable pageable,
            @Parameter(
                    name = "name",
                    required = false,
                    in = ParameterIn.QUERY,
                    description = "The text to search for in client names. " +
                            "Only clients whose names contain this text will be returned."
            )
            @RequestParam(value = "name", defaultValue = "")
            String name) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(clientService.findClientsByNameContainingIgnoreCase(name, pageable));
    }

    @Operation(
            summary = "Get a client by ID",
            description = "Returns a client based on their ID.",
            tags = "Clients",
            responses = {
                    @ApiResponse(
                            description = "Ok",
                            responseCode = "200",
                            content = @Content(
                                    schema = @Schema(
                                            implementation = ClientResponseVO.class))
                    ),
                    @ApiResponse(
                            description = "Forbidden",
                            responseCode = "403",
                            content = @Content
                    ),
                    @ApiResponse(
                            description = "Not Found",
                            responseCode = "404",
                            content = @Content
                    ),
                    @ApiResponse(
                            description = "Internal Server Error",
                            responseCode = "500",
                            content = @Content
                    )
            }
    )
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ClientResponseVO> findById(
            @Parameter(
                    description = "ID of the client to be retrieved. Must be a valid UUID format.",
                    in = ParameterIn.PATH,
                    required = true)
            @PathVariable(value = "id")
            UUID id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(clientService.findById(id));
    }
}

