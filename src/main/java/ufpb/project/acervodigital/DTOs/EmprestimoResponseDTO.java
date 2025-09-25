package ufpb.project.acervodigital.DTOs;

import io.swagger.v3.oas.annotations.media.Schema;
import ufpb.project.acervodigital.models.enums.StatusEmprestimo;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class EmprestimoResponseDTO {
    @Schema(description = "Id do empréstimo")
    private Long id;
    @Schema(description = "Id do usuário")
    private Long userId;
    @Schema(description = "Nome do usuário")
    private String nomeUser;
    @Schema(description = "Id do ativo digital")
    private Long ativoId;
    @Schema(description = "Titulo do ativo digital")
    private String tituloAtivo;
    @Schema(description = "Data em que ocorreu o empréstimo")
    private LocalDate dataEmprestimo;
    @Schema(description = "Data de expiração do empréstimo")
    private LocalDate dataDevolucao;
    @Schema(description = "Status do empréstimo (ATIVO, DEVOLVIDO, EXPIRADO)")
    private StatusEmprestimo status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getNomeUser() {
        return nomeUser;
    }

    public void setNomeUser(String nomeUser) {
        this.nomeUser = nomeUser;
    }

    public Long getAtivoId() {
        return ativoId;
    }

    public void setAtivoId(Long ativoId) {
        this.ativoId = ativoId;
    }

    public String getTituloAtivo() {
        return tituloAtivo;
    }

    public void setTituloAtivo(String tituloAtivo) {
        this.tituloAtivo = tituloAtivo;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(LocalDate dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(LocalDate dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public StatusEmprestimo getStatus() {
        return status;
    }

    public void setStatus(StatusEmprestimo status) {
        this.status = status;
    }
}
