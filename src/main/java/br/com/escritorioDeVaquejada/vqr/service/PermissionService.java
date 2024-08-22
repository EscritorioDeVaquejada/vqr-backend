package br.com.escritorioDeVaquejada.vqr.service;

import br.com.escritorioDeVaquejada.vqr.model.PermissionModel;

import java.util.Optional;

public interface PermissionService {
    Optional<PermissionModel> findByDescription(String description);
    PermissionModel savePermission(PermissionModel newPermission);
}
