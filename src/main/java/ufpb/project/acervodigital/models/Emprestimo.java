package ufpb.project.acervodigital.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import ufpb.project.acervodigital.models.enums.StatusEmprestimo;

import java.time.LocalDate;

@Entity
public class Emprestimo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "emprestimo_id")
    private Long id;

    @Column(name = "data_emprestimo")
    private LocalDate dataEmprestimo;

    @Column(name = "data_devolução")
    private LocalDate dataDevolucao;

    @Enumerated(EnumType.STRING)
    private StatusEmprestimo status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ativo_id")
    @JsonBackReference
    private AtivoDigital ativo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
