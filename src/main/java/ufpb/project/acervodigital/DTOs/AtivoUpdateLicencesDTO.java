package ufpb.project.acervodigital.DTOs;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class AtivoUpdateLicencesDTO {
    @NotNull(message = "O numero de licenças não pode ser vazio")
    @Min(0)
    private Integer licence;

    public Integer getLicence() {
        return licence;
    }

    public void setLicence(Integer licence) {
        this.licence = licence;
    }
}
