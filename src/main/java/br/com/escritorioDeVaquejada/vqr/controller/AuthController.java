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
        if(errorsInTheRequest.hasErrors() || !cpfIsValid(newUser.getCpf())){
            throw new BadRequestException("Invalid data!");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.register(newUser));
    }

    //todo terminar a implementação do método de verificação dos dois últimos dígitos do cpf
    private boolean cpfIsValid(String cpf){
        /*
        String[] cpfFragments = cpf.split("(\\.)|(-)");

        String unformattedCpf = Arrays.stream(cpfFragments).reduce("", (str1, str2) -> str1 + str2);

        List<Integer> individualCpfNumbers = new ArrayList<>();

        for(int index=0; index<9; index++){
           individualCpfNumbers.add(Integer.parseInt(unformattedCpf.charAt(index)+""));
        }
        */
        return true;
    }
}
