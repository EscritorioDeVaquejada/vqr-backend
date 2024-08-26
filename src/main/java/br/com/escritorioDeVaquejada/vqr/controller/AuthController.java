package br.com.escritorioDeVaquejada.vqr.controller;

import br.com.escritorioDeVaquejada.vqr.exception.BadRequestException;
import br.com.escritorioDeVaquejada.vqr.service.AuthService;
import br.com.escritorioDeVaquejada.vqr.vo.auth.AccountCredentialsVO;
import br.com.escritorioDeVaquejada.vqr.vo.auth.TokenVO;
import br.com.escritorioDeVaquejada.vqr.vo.auth.UserRegistrationVO;
import br.com.escritorioDeVaquejada.vqr.vo.user.UserResponseVO;
import io.swagger.v3.oas.annotations.Operation;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@Tag(name = "Authentication and Registration",
        description = "Endpoints for user authentication and registration operations.")
public class AuthController {
    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    //todo verificar se há necessidade de configurar uma saída 404 ou similar para um user não encontrado
    @PostMapping(
            value = "/login",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
            summary = "Get access token",
            description = "Signs in a user by validating their credentials and returning an " +
                    "access token if the authentication is successful.",
            tags = "Authentication and Registration",
            responses = {
                    @ApiResponse(
                            description = "Ok",
                            responseCode = "200",
                            content = @Content(schema = @Schema(implementation = TokenVO.class)
                    )),
                    @ApiResponse(description = "Bad Request", responseCode = "400",
                            content = @Content),
                    @ApiResponse(description = "Internal Sever Error", responseCode = "500",
                            content = @Content)
            })
    public ResponseEntity<TokenVO> login(
            @RequestBody @Valid AccountCredentialsVO data,
            BindingResult errorsInTheRequestBody) {
        if (errorsInTheRequestBody.hasErrors()) {
            throw new BadRequestException("Invalid client request");
        }
        TokenVO token = authService.login(data);
        //todo verificar se o token pode chegar até este ponto sendo null
        if (token == null) {
            throw new RuntimeException("Server cannot generate access token");
        }
        return ResponseEntity
                .ok()
                .body(token);
    }

    @PostMapping(
            value = "/register",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
            summary = "Register a user",
            description = "Registers a new user by creating an account with the provided " +
                    "information. If successful, returns the details of the newly created user",
            tags = "Authentication and Registration",
            responses = {
                    @ApiResponse(
                            description = "Created",
                            responseCode = "204",
                            content = @Content(
                                    schema = @Schema(implementation = UserResponseVO.class))),
                    @ApiResponse(description = "Bad Request", responseCode = "400",
                            content = @Content),
                    @ApiResponse(description = "Forbidden", responseCode = "403",
                            content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500",
                            content = @Content)
            })
    public ResponseEntity<UserResponseVO> register(
            @RequestBody @Valid UserRegistrationVO newUser,
            BindingResult errorsInTheRequest) throws BadRequestException {
        if (errorsInTheRequest.hasErrors() || !lastTwoDigitsOfTheCpfAreValid(newUser.getCpf())) {
            throw new BadRequestException("Invalid data!");
        }
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(authService.register(newUser));
    }

    private boolean lastTwoDigitsOfTheCpfAreValid(String cpf) {
        String unformattedCpf = cpf.replaceAll("[^\\d]", "");
        if (unformattedCpf.length() != 11) {
            return false;
        }
        int penultimateDigit = calculateCpfDigit(unformattedCpf, 10);
        int lastDigit = calculateCpfDigit(unformattedCpf, 11, penultimateDigit);
        return unformattedCpf.charAt(9) - '0' == penultimateDigit &&
                unformattedCpf.charAt(10) - '0' == lastDigit;
    }

    private int calculateCpfDigit(String cpf, int factor) {
        return calculateCpfDigit(cpf, factor, 0);
    }

    private int calculateCpfDigit(String cpf, int factor, int previousDigit) {
        int sum = 0;
        for (int i = 0; i < factor - 1; i++) {
            sum += (cpf.charAt(i) - '0') * (factor - i);
        }
        sum += previousDigit * factor;
        int remainder = sum % 11;
        return remainder < 2 ? 0 : 11 - remainder;
    }
}
