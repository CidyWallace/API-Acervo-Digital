package ufpb.project.acervodigital.DTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ReservaRequestDTO {
    @NotNull(message = "É preciso informa um usuário para ser feito uma reserva")
    private Long userId;
    @NotNull(message = "É preciso informa um ativo para ser feito uma reserva")
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
