package ufpb.project.acervodigital.DTOs;

public class ReservaRequestDTO {
    private Long userId;
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
