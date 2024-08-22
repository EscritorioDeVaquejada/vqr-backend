package br.com.escritorioDeVaquejada.vqr.service;

import br.com.escritorioDeVaquejada.vqr.vo.AccountCredentialsVO;
import br.com.escritorioDeVaquejada.vqr.vo.TokenVO;
import br.com.escritorioDeVaquejada.vqr.vo.UserRegistrationVO;
import br.com.escritorioDeVaquejada.vqr.vo.UserResponseVO;

public interface AuthService {
    TokenVO login(AccountCredentialsVO data);
    UserResponseVO register(UserRegistrationVO newUser);
}
