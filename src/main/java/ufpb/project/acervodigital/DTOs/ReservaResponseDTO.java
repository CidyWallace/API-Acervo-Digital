package ufpb.project.acervodigital.DTOs;

import ufpb.project.acervodigital.models.enums.StatusReserva;

import java.time.LocalDateTime;

public class ReservaResponseDTO {
    private Long id;
    private Long userId;
    private String nomeUser;
    private Long ativoId;
    private String tituloAtivo;
    private LocalDateTime dataReserva;
    private StatusReserva status;
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
