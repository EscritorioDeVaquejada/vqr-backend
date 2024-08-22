package br.com.escritorioDeVaquejada.vqr.controller;

import br.com.escritorioDeVaquejada.vqr.exception.BadRequestException;
import br.com.escritorioDeVaquejada.vqr.service.AuthService;
import br.com.escritorioDeVaquejada.vqr.vo.AccountCredentialsVO;
import br.com.escritorioDeVaquejada.vqr.vo.UserRegistrationVO;
import br.com.escritorioDeVaquejada.vqr.vo.TokenVO;
import br.com.escritorioDeVaquejada.vqr.vo.UserResponseVO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<Object> login(
            @RequestBody @Valid AccountCredentialsVO data,
            BindingResult errorsInTheRequestBody){
        if(errorsInTheRequestBody.hasErrors()){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request");
        }
        TokenVO token = authService.login(data);
        if(token == null){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request");
        }
        return ResponseEntity.ok().body(token);
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponseVO> register(
            @RequestBody @Valid UserRegistrationVO newUser,
            BindingResult errorsInTheRequest) throws BadRequestException {
        if(errorsInTheRequest.hasErrors() || !lastTwoDigitsOfTheCpfAreValid(newUser.getCpf())){
            throw new BadRequestException("Invalid data!");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.register(newUser));
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
