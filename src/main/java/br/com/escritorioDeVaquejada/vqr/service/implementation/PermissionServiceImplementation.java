package br.com.escritorioDeVaquejada.vqr.service.implementation;

import br.com.escritorioDeVaquejada.vqr.model.PermissionModel;
import br.com.escritorioDeVaquejada.vqr.repository.PermissionRepository;
import br.com.escritorioDeVaquejada.vqr.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PermissionServiceImplementation implements PermissionService {

    private final PermissionRepository permissionRepository;

    @Autowired
    public PermissionServiceImplementation(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }

    public Optional<PermissionModel> findByDescription(String description){
        return permissionRepository.findByDescription(description);
    }

    public PermissionModel savePermission(PermissionModel newPermission){
        return permissionRepository.save(newPermission);
    }
}
