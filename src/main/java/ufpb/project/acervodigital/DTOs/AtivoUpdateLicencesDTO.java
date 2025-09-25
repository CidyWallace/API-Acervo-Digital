package ufpb.project.acervodigital.DTOs;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class AtivoUpdateLicencesDTO {
    @NotNull(message = "O numero de licenças não pode ser vazio")
    @Min(0)
    @Schema(description = "Total de licenças disponíveis para empréstimos do ativo digital")
    private Integer licence;

    public Integer getLicence() {
        return licence;
    }

    public void setLicence(Integer licence) {
        this.licence = licence;
    }
}
