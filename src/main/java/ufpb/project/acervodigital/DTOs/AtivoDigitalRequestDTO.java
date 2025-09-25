package ufpb.project.acervodigital.DTOs;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import ufpb.project.acervodigital.models.enums.FormatoAtivo;
import ufpb.project.acervodigital.validation.EnumValue;

public class AtivoDigitalRequestDTO {
    @Schema(description = "titulo do ativo digital")
    @NotBlank(message = "O titulo não pode estar em branco")
    private String titulo;
    @Schema(description = "Nome do autor")
    @NotBlank(message = "O autor não pode estar em branco")
    private String autor;
    @Schema(description = "Formato do ativo Digital (EBOOK, AUDIOBOOK)")
    @NotNull(message = "O formato não pode ser nulo")
    @EnumValue(
            enumClass = FormatoAtivo.class,
            message = "O formato informado é inválido! Formatos válidos: EBOOK, AUDIOBOOK"
    )
    private String formato;
    @Schema(description = "Total de licenças do ativo digital")
    @Min(0)
    private Integer totalLicencas;
    @Schema(description = "Total de licenças disponíveis do ativo digital")
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
