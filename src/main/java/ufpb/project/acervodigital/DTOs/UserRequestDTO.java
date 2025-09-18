package ufpb.project.acervodigital.DTOs;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserRequestDTO {
    @NotBlank(message = "O nome não pode estar em branco")
    private String nome;
    @NotBlank(message = "O email não pode estar em branco")
    @Email
    private String email;
    @NotBlank(message = "A senha não pode estar em branco")
    @Size(min = 6, message = "A senha precisa de no mínimo 6 caracteres")
    private String senha;
    @NotBlank(message = "O número do cartão da biblioteca não pode estar em branco")
    @Min(value = 6, message = "O número do cartão da biblioteca precisa de no mínimo 6 dígitos")
    private String numeroCartao;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNumeroCartao() {
        return numeroCartao;
    }

    public void setNumeroCartao(String numeroCartao) {
        this.numeroCartao = numeroCartao;
    }
}
