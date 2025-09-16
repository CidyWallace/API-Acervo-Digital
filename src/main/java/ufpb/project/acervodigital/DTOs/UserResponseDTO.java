package ufpb.project.acervodigital.DTOs;

import ufpb.project.acervodigital.models.enums.StatusUsuario;

import java.time.LocalDate;

public class UserResponseDTO {
    private Long id;
    private String name;
    private String numeroCartao;
    private String email;
    private LocalDate dataCriacao;
    private StatusUsuario status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumeroCartao() {
        return numeroCartao;
    }

    public void setNumeroCartao(String numeroCartao) {
        this.numeroCartao = numeroCartao;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public StatusUsuario getStatus() {
        return status;
    }

    public void setStatus(StatusUsuario status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", numeroCartao='" + numeroCartao + '\'' +
                ", email='" + email + '\'' +
                ", dataCriacao=" + dataCriacao +
                ", status=" + status +
                '}';
    }
}
