package com.ngola.agenciaviagensbackend.dto;

public record AuthResponse(String accessToken, long expiresIn) {
}