package ufpb.project.acervodigital.models;

import jakarta.persistence.*;
import ufpb.project.acervodigital.models.enums.FormatoAtivo;

@Entity
public class AtivoDigital {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    private String autor;

    @Enumerated(EnumType.STRING)
    private FormatoAtivo formato;

    @Column(name = "total_licencas")
    private Integer totalLicencas;

    @Column(name = "licencas_disponiveis")
    private Integer licencasDisponiveis;

    public Integer getLicencasDisponiveis() {
        return licencasDisponiveis;
    }

    public void setLicencasDisponiveis(Integer licencasDisponiveis) {
        this.licencasDisponiveis = licencasDisponiveis;
    }

    public Integer getTotalLicencas() {
        return totalLicencas;
    }

    public void setTotalLicencas(Integer totalLicencas) {
        this.totalLicencas = totalLicencas;
    }

    public FormatoAtivo getFormato() {
        return formato;
    }

    public void setFormato(FormatoAtivo formato) {
        this.formato = formato;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

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
}
