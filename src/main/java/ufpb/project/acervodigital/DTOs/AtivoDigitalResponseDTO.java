package ufpb.project.acervodigital.DTOs;

import io.swagger.v3.oas.annotations.media.Schema;
import ufpb.project.acervodigital.models.enums.FormatoAtivo;

public class AtivoDigitalResponseDTO {
    @Schema(description = "Id do ativo digital")
    private Long id;
    @Schema(description = "Titulo do ativo digital")
    private String titulo;
    @Schema(description = "Autor do ativo digital")
    private String autor;
    @Schema(description = "Formato do ativo digital")
    private FormatoAtivo formato;
    @Schema(description = "Total de licenças do ativo digital")
    private Integer totalLicencas;
    @Schema(description = "Total de licenças disponíveis para empréstimos do ativo digital")
    private Integer licencasDisponiveis;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public FormatoAtivo getFormato() {
        return formato;
    }

    public void setFormato(FormatoAtivo formato) {
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
