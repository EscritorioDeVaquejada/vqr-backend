package br.com.escritorioDeVaquejada.vqr.service;

import br.com.escritorioDeVaquejada.vqr.vo.auth.AccountCredentialsVO;
import br.com.escritorioDeVaquejada.vqr.vo.auth.TokenVO;
import br.com.escritorioDeVaquejada.vqr.vo.auth.UserRegistrationVO;
import br.com.escritorioDeVaquejada.vqr.vo.user.UserResponseVO;

public interface AuthService {
    TokenVO login(AccountCredentialsVO data);
    UserResponseVO register(UserRegistrationVO newUser);
}
