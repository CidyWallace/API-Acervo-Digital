package ufpb.project.acervodigital.DTOs;

import jakarta.validation.constraints.NotBlank;

public class EmprestimoRequestDTO {
    @NotBlank(message = "É preciso informa um usuário para ser feito um empréstimo")
    private Long usuarioId;
    @NotBlank(message = "É preciso informa um ativo para ser feito um empréstimo")
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
