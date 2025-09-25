package ufpb.project.acervodigital.DTOs;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public class EmprestimoRequestDTO {
    @NotNull(message = "É preciso informa um usuário para ser feito um empréstimo")
    @Schema(description = "Id do usuário que irá realizar o empréstimo")
    private Long usuarioId;
    @NotNull(message = "É preciso informa um ativo para ser feito um empréstimo")
    @Schema(description = "Id do ativo digital")
    private Long ativoId;

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Long getAtivoId() {
        return ativoId;
    }

    public void setAtivoId(Long ativoId) {
        this.ativoId = ativoId;
    }
}
