package ufpb.project.acervodigital.DTOs;

import jakarta.validation.constraints.NotBlank;

public class ReservaRequestDTO {
    @NotBlank(message = "É preciso informa um usuário para ser feito uma reserva")
    private Long userId;
    @NotBlank(message = "É preciso informa um ativo para ser feito uma reserva")
    private Long ativoId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getAtivoId() {
        return ativoId;
    }

    public void setAtivoId(Long ativoId) {
        this.ativoId = ativoId;
    }
}
