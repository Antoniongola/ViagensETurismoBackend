package com.ngola.agenciaviagensbackend.service;

import com.ngola.agenciaviagensbackend.dto.AuthResponse;
import com.ngola.agenciaviagensbackend.dto.LoginDto;
import com.ngola.agenciaviagensbackend.model.Role;
import com.ngola.agenciaviagensbackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final PasswordEncoder encoder;
    private final UserRepository repository;
    private final JwtEncoder jwtEncoder;

    public AuthResponse login(LoginDto dto){
        var user = repository.findByEmail(dto.email());

        if(user.isEmpty() || !isLoginCorrect(dto)){
            throw new BadCredentialsException("user or password errados");
        }

        var now = Instant.now();
        var expiresIn = 72000L;
        var scopes = user.get().getRoles()
                .stream()
                .map(Role::name)
                .collect(Collectors.joining(" "));

        var claims = JwtClaimsSet.builder()
                .issuer("Ngola-ISPMEDIA")
                .subject(dto.email())
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiresIn))
                .claim("scope", scopes)
                .build();

        String jwt = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

        return new AuthResponse(jwt, expiresIn);
    }

    public AuthResponse generateRefreshToken(LoginDto dto) {
        var user = repository.findByEmail(dto.email());

        if(user.isEmpty()) {
            throw new BadCredentialsException("user or password errados");
        }

        var now = Instant.now();
        var expiresIn = 7200L; // 30 dias em segundos (30 * 24 * 60 * 60)
        var claims = JwtClaimsSet.builder()
                .issuer("Ngola-ISPMEDIA")
                .subject(dto.email())
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiresIn))
                .claim("type", "refresh")
                .build();

        String refreshToken = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

        return new AuthResponse(refreshToken, expiresIn);
    }


    private boolean isLoginCorrect(LoginDto dto) {
        if(repository.findByEmail(dto.email()).isPresent()){
            if(encoder.matches(dto.password(), repository.findByEmail(dto.email()).get().getPassword()))
                return true;
        }

        return false;
    }
}
