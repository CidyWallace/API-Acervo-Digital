package ufpb.project.acervodigital.DTOs;

import io.swagger.v3.oas.annotations.media.Schema;
import ufpb.project.acervodigital.models.enums.StatusUsuario;

import java.time.LocalDate;

public class UserResponseDTO {
    @Schema(description = "Id do usuário")
    private Long id;
    @Schema(description = "Nome do usuário")
    private String nome;
    @Schema(description = "Numéro do cartão da biblioteca do usuário")
    private String numeroCartao;
    @Schema(description = "Email do usuário")
    private String email;
    @Schema(description = "Data de criação da conta do usuário")
    private LocalDate dataCriacao;
    @Schema(description = "Status do usuário (ATIVA, SUSPENSA)")
    private StatusUsuario status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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
                ", name='" + nome + '\'' +
                ", numeroCartao='" + numeroCartao + '\'' +
                ", email='" + email + '\'' +
                ", dataCriacao=" + dataCriacao +
                ", status=" + status +
                '}';
    }
}
