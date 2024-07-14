package br.com.escritorioDeVaquejada.vqr.repositories;

import br.com.escritorioDeVaquejada.vqr.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<UserModel, UUID> {
}
