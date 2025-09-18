package ufpb.project.acervodigital.DTOs;

import jakarta.validation.constraints.NotNull;
import ufpb.project.acervodigital.models.enums.StatusUsuario;
import ufpb.project.acervodigital.validation.EnumValue;

public class UserStatusRequestDTO {
    @NotNull(message = "O status não pode ser nulo")
    @EnumValue(
            enumClass = StatusUsuario.class,
            message = "O status informado é inválido! status válidos:  ATIVO, SUSPENSO"
    )
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
