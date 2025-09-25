package ufpb.project.acervodigital.DTOs;

import io.swagger.v3.oas.annotations.media.Schema;

public class AuthResponseDTO {
    @Schema(description = "Token de acesso")
    private String token;

    public AuthResponseDTO(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
