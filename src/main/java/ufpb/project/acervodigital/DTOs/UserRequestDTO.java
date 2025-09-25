package ufpb.project.acervodigital.DTOs;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import ufpb.project.acervodigital.models.enums.FormatoAtivo;
import ufpb.project.acervodigital.models.enums.Role;
import ufpb.project.acervodigital.validation.EnumValue;

public class UserRequestDTO {
    @NotBlank(message = "O nome não pode estar em branco")
    @Schema(description = "Nome do usuário")
    private String nome;
    @NotBlank(message = "O email não pode estar em branco")
    @Email
    @Schema(description = "Email do usuário")
    private String email;
    @NotBlank(message = "A senha não pode estar em branco")
    @Size(min = 6, message = "A senha precisa de no mínimo 6 caracteres")
    @Schema(description = "Senha do usuário")
    private String senha;
    @NotBlank(message = "O número do cartão da biblioteca não pode estar em branco")
    @Min(value = 6, message = "O número do cartão da biblioteca precisa de no mínimo 6 dígitos")
    @Schema(description = "Numéro do cartão da biblioteca do usuário")
    private String numeroCartao;
    @NotNull(message = "A role não pode ficar em branco")
    @EnumValue(
            enumClass = Role.class,
            message = "A role informado é inválido! Formatos válidos: USER, ADMIN"
    )
    @Schema(description = "Regras de acessos que o usuário possui (USER, ADMIN)")
    private String role;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

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
