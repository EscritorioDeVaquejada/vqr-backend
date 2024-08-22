package br.com.escritorioDeVaquejada.vqr.repository;

import br.com.escritorioDeVaquejada.vqr.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<UserModel, UUID> {
    Optional<UserModel> findByUsername(String username);
    Optional<UserModel> findByUsernameOrCpf(String username, String cpf);
}
