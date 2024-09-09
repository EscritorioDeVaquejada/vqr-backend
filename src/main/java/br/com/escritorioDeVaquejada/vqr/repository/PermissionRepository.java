package br.com.escritorioDeVaquejada.vqr.repository;

import br.com.escritorioDeVaquejada.vqr.model.PermissionModel;
import br.com.escritorioDeVaquejada.vqr.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PermissionRepository extends JpaRepository<PermissionModel, UUID> {
    Optional<PermissionModel> findByDescription(String description);
}
