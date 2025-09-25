package ufpb.project.acervodigital.DTOs;

import io.swagger.v3.oas.annotations.media.Schema;
import ufpb.project.acervodigital.models.enums.StatusReserva;

import java.time.LocalDateTime;

public class ReservaResponseDTO {
    @Schema(description = "Id da reserva")
    private Long id;
    @Schema(description = "Id do usuário")
    private Long userId;
    @Schema(description = "Nome do usuário")
    private String nomeUser;
    @Schema(description = "Id do ativo digital")
    private Long ativoId;
    @Schema(description = "Titulo do ativo digital")
    private String tituloAtivo;
    @Schema(description = "Data que ocorreu a reserva")
    private LocalDateTime dataReserva;
    @Schema(description = "Status da reserva (ATIVA, ATENDIDA, CANCELADA")
    private StatusReserva status;
    @Schema(description = "Posição na fila em que o usuário se encontra")
    private Long posicaoFila;

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

    public LocalDateTime getDataReserva() {
        return dataReserva;
    }

    public void setDataReserva(LocalDateTime dataReserva) {
        this.dataReserva = dataReserva;
    }

    public StatusReserva getStatus() {
        return status;
    }

    public void setStatus(StatusReserva status) {
        this.status = status;
    }

    public Long getPosicaoFila() {
        return posicaoFila;
    }

    public void setPosicaoFila(Long posicaoFila) {
        this.posicaoFila = posicaoFila;
    }
}
