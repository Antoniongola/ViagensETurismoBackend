package com.ngola.agenciaviagensbackend.service;

import com.ngola.agenciaviagensbackend.model.User;
import com.ngola.agenciaviagensbackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    public User saveUser(User user) {
        user.setSenha(encoder.encode(user.getSenha()));
        if(userRepository.findByEmail(user.getEmail()).isEmpty())
            return userRepository.save(user);

        return null;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }


}
