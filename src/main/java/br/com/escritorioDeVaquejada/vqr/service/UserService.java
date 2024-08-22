package br.com.escritorioDeVaquejada.vqr.service;

import br.com.escritorioDeVaquejada.vqr.model.UserModel;
import br.com.escritorioDeVaquejada.vqr.vo.UserRegistrationVO;
import br.com.escritorioDeVaquejada.vqr.vo.UserResponseVO;

import java.util.Optional;

public interface UserService {
    UserModel loadUserByUsername(String username);
    Optional<UserModel> findByUsernameOrCpf(String username, String cpf);
    UserResponseVO saveUser(UserRegistrationVO newUser);
}
