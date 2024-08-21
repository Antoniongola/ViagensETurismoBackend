package com.ngola.agenciaviagensbackend.controller;

import com.ngola.agenciaviagensbackend.dto.AuthResponse;
import com.ngola.agenciaviagensbackend.dto.LoginDto;
import com.ngola.agenciaviagensbackend.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthenticationService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginDto loginDto){
        if(this.authService.login(loginDto) != null)
            return ResponseEntity.ok(this.authService.login(loginDto));

        return ResponseEntity.status(401).build();
    }

    @PostMapping("/token/refresh")
    public ResponseEntity<AuthResponse> refreshToken(@RequestBody LoginDto loginDto){
        if(this.authService.generateRefreshToken(loginDto) != null)
            return ResponseEntity.ok(this.authService.generateRefreshToken(loginDto));

        return ResponseEntity.status(401).build();
    }
}
