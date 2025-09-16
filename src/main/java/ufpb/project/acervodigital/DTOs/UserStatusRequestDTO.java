package ufpb.project.acervodigital.DTOs;

import ufpb.project.acervodigital.models.enums.StatusUsuario;

public class UserStatusRequestDTO {
    private StatusUsuario status;

    public StatusUsuario getStatus() {
        return status;
    }

    public void setStatus(StatusUsuario status) {
        this.status = status;
    }
}
