package com.ngola.agenciaviagensbackend.security.AdmindInitialConfig;

import com.ngola.agenciaviagensbackend.model.Role;
import com.ngola.agenciaviagensbackend.model.User;
import com.ngola.agenciaviagensbackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AdminConfig implements CommandLineRunner {
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    @Override
    public void run(String... args) throws Exception {
        User user = new User();
        user.setEmail("Anthonyngola@hotmail.com");
        user.setNome("Anthony Domingos Lopes Ngola");
        user.setSenha(encoder.encode("Fatima12$"));
        user.setRoles(List.of(Role.ADMIN, Role.GESTOR, Role.USER));
        if(userRepository.findByEmail(user.getEmail()).isEmpty())
            userRepository.save(user);
    }
}
