package ufpb.project.acervodigital.models;

import jakarta.persistence.*;
import ufpb.project.acervodigital.models.enums.StatusUsuario;

import java.time.LocalDate;
import java.util.Collection;

@Entity
@Table(name = "USUARIOS")
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "numero_cartao", unique = true)
    private String numeroCartao;

    private String nome;

    @Column(unique = true)
    private String email;

    private String senha;

    @Column(name = "data_criacao")
    private LocalDate dataCriacao;

    @Enumerated(EnumType.STRING)
    private StatusUsuario status;

    @OneToMany(mappedBy = "user")
    private Collection<Emprestimo> emprestimos;

    @OneToMany(mappedBy = "user")
    private Collection<Reserva> reservas;

    public StatusUsuario getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = StatusUsuario.valueOf(status.toUpperCase());
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
