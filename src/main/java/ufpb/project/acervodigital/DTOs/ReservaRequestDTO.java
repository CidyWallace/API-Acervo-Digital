package ufpb.project.acervodigital.DTOs;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public class ReservaRequestDTO {
    @NotNull(message = "É preciso informa um usuário para ser feito uma reserva")
    @Schema(description = "Id do usuário que irá fazer o emprestimo")
    private Long userId;
    @NotNull(message = "É preciso informa um ativo para ser feito uma reserva")
    @Schema(description = "Id do ativo digital")
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
