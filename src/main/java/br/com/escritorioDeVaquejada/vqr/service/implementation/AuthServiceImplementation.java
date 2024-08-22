package br.com.escritorioDeVaquejada.vqr.service.implementation;

import br.com.escritorioDeVaquejada.vqr.exception.BadRequestException;
import br.com.escritorioDeVaquejada.vqr.model.UserModel;
import br.com.escritorioDeVaquejada.vqr.security.jwt.JwtTokenProvider;
import br.com.escritorioDeVaquejada.vqr.service.AuthService;
import br.com.escritorioDeVaquejada.vqr.service.UserService;
import br.com.escritorioDeVaquejada.vqr.vo.AccountCredentialsVO;
import br.com.escritorioDeVaquejada.vqr.vo.TokenVO;
import br.com.escritorioDeVaquejada.vqr.vo.UserRegistrationVO;
import br.com.escritorioDeVaquejada.vqr.vo.UserResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthServiceImplementation implements AuthService {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider tokenProvider;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthServiceImplementation(
            AuthenticationManager authenticationManager,
            JwtTokenProvider tokenProvider,
            UserService userService,
            PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    //todo adicionar um método no exception handler para uma BadCredentialsException
    //todo adicionar um método no exception handler para uma UsernameNotFoundException
    public TokenVO login(AccountCredentialsVO data)
            throws BadCredentialsException, UsernameNotFoundException{
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(data.getUsername(), data.getPassword()));
            UserModel user = userService.loadUserByUsername(data.getUsername());
            TokenVO tokenResponse = new TokenVO();
            if(user != null){
                tokenResponse = tokenProvider.createAccessToken(data.getUsername(), user.getRoles());
            }else{
                throw new UsernameNotFoundException("Username "+data.getUsername()+" not found!");
            }
            return tokenResponse;
        }catch (BadCredentialsException e){
            throw new BadCredentialsException("Invalid username/password supplied!");
        }
    }

    //todo verificar a necessidade de suporte a mais roles no sistema
    public UserResponseVO register(UserRegistrationVO newUser) throws BadRequestException{
        Optional<UserModel> userWithTheSameNameOrCPF =
                userService.findByUsernameOrCpf(newUser.getUsername(), newUser.getCpf());
        if(userWithTheSameNameOrCPF.isPresent()){
            throw new BadRequestException("Invalid username/cpf supplied!");
        }
        List<String> permissions = List.of("ROLE_ADMIN");
        newUser.setPermissions(permissions);
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        return userService.saveUser(newUser);
    }
}
