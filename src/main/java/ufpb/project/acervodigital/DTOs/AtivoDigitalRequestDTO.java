package ufpb.project.acervodigital.DTOs;

import ufpb.project.acervodigital.models.enums.FormatoAtivo;

public class AtivoDigitalRequestDTO {
    private String titulo;
    private String autor;
    private FormatoAtivo formato;
    private Integer totalLicencas;
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
