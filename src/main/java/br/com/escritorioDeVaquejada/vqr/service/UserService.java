package br.com.escritorioDeVaquejada.vqr.service;

import br.com.escritorioDeVaquejada.vqr.model.UserModel;
import br.com.escritorioDeVaquejada.vqr.vo.auth.UserRegistrationVO;
import br.com.escritorioDeVaquejada.vqr.vo.user.UserResponseVO;

import java.util.Optional;

public interface UserService {
    UserModel loadUserByUsername(String username);
    Optional<UserModel> findByUsernameOrCpf(String username, String cpf);
    UserResponseVO saveUser(UserRegistrationVO newUser);
}
