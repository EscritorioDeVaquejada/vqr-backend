package br.com.escritorioDeVaquejada.vqr.service.implementation;

import br.com.escritorioDeVaquejada.vqr.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationServiceImplementation implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public AuthorizationServiceImplementation(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //todo criar um método para uma UsernameNotFoundException no exception handler
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userRepository.findByLogin(username);
    }
}
