package ufpb.project.acervodigital.DTOs;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import ufpb.project.acervodigital.models.enums.FormatoAtivo;
import ufpb.project.acervodigital.validation.EnumValue;

public class AtivoDigitalRequestDTO {
    @NotBlank(message = "O titulo não pode estar em branco")
    private String titulo;
    @NotBlank(message = "O autor não pode estar em branco")
    private String autor;
    @NotNull(message = "O formato não pode ser nulo")
    @EnumValue(
            enumClass = FormatoAtivo.class,
            message = "O formato informado é inválido! Formatos válidos: EBOOK, AUDIOBOOK"
    )
    private String formato;
    @Min(0)
    private Integer totalLicencas;
    @Min(0)
    private Integer licencasDisponiveis;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getFormato() {
        return formato;
    }

    public void setFormato(String formato) {
        this.formato = formato;
    }

    public Integer getTotalLicencas() {
        return totalLicencas;
    }

    public void setTotalLicencas(Integer totalLicencas) {
        this.totalLicencas = totalLicencas;
    }

    public Integer getLicencasDisponiveis() {
        return licencasDisponiveis;
    }

    public void setLicencasDisponiveis(Integer licencasDisponiveis) {
        this.licencasDisponiveis = licencasDisponiveis;
    }
}
