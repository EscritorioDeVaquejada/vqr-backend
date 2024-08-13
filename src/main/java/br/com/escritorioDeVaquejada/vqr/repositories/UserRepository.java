package br.com.escritorioDeVaquejada.vqr.repositories;

import br.com.escritorioDeVaquejada.vqr.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.UUID;

public interface UserRepository extends JpaRepository<UserModel, UUID> {
    /*
    @Query("select user from UserModel user where user.login = :login")
    UserDetails findByLogin(@Param("login") String login);
    */
    UserDetails findByLogin(String login);
}
