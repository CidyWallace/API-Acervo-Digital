package ufpb.project.acervodigital.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import ufpb.project.acervodigital.models.enums.StatusReserva;

import java.time.LocalDateTime;

@Entity
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reserva_id")
    private Long id;

    @Column(name = "data_reserva")
    private LocalDateTime dataReserva;

    @Enumerated(EnumType.STRING)
    private StatusReserva status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

    @ManyToOne
    @JoinColumn(name = "ativo_id")
    @JsonBackReference
    private AtivoDigital ativo;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public AtivoDigital getAtivo() {
        return ativo;
    }

    public void setAtivo(AtivoDigital ativo) {
        this.ativo = ativo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
