package br.com.escritorioDeVaquejada.vqr.service.implementation;

import br.com.escritorioDeVaquejada.vqr.exception.ResourceNotFoundException;
import br.com.escritorioDeVaquejada.vqr.mapper.Mapper;
import br.com.escritorioDeVaquejada.vqr.model.PermissionModel;
import br.com.escritorioDeVaquejada.vqr.model.UserModel;
import br.com.escritorioDeVaquejada.vqr.repository.UserRepository;
import br.com.escritorioDeVaquejada.vqr.service.PermissionService;
import br.com.escritorioDeVaquejada.vqr.service.UserService;
import br.com.escritorioDeVaquejada.vqr.vo.auth.UserRegistrationVO;
import br.com.escritorioDeVaquejada.vqr.vo.user.UserResponseVO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImplementation implements UserDetailsService, UserService {
    private final UserRepository userRepository;
    private final PermissionService permissionService;
    private final Mapper mapper;

    @Autowired
    public UserServiceImplementation(
            UserRepository userRepository,
            Mapper mapper,
            PermissionService permissionService) {
        this.userRepository = userRepository;
        this.mapper = mapper;
        this.permissionService = permissionService;
    }

    //todo criar um método no exception handler para tratar uma UsernameNotFoundException
    @Override
    public UserModel loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() ->
                new UsernameNotFoundException("Username "+ username +" not found!"));
    }

    public Optional<UserModel> findByUsernameOrCpf(String username, String cpf)
            throws ResourceNotFoundException{
        return userRepository.findByUsernameOrCpf(username, cpf);
    }

    //todo substituir as consultas individuais e o laço de repetição por uma versão mais eficiente e otmizada
    @Override
    @Transactional
    public UserResponseVO saveUser(UserRegistrationVO newUser, List<String> permissions) {
        UserModel userToBeSaved = mapper.parseObject(newUser, UserModel.class);
        List<PermissionModel> registeredUserPermissions = new ArrayList<>();
        for(String permission: permissions){
            Optional<PermissionModel> registeredPermission =
                    permissionService.findByDescription(permission);
            if(registeredPermission.isEmpty()){
                PermissionModel newPermission = permissionService.savePermission(
                        new PermissionModel(permission));
                registeredUserPermissions.add(newPermission);
            }else{
                registeredUserPermissions.add(registeredPermission.get());
            }
        }
        userToBeSaved.setPermissions(registeredUserPermissions);
        userToBeSaved.setAccountNonExpired(true);
        userToBeSaved.setAccountNonLocked(true);
        userToBeSaved.setCredentialsNonExpired(true);
        userToBeSaved.setEnabled(true);
        UserModel savedUser = userRepository.save(userToBeSaved);
        UserResponseVO savedUserAsVO = mapper.parseObject(savedUser, UserResponseVO.class);
        savedUserAsVO.setPermissions(savedUser.getRoles());
        return savedUserAsVO;
    }
}
