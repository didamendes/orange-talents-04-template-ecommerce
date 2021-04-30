package br.com.zup.ecommerce.usuario;

import br.com.zup.ecommerce.validatores.UniqueValid;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UsuarioRequest {

    @NotBlank
    @Email
    @UniqueValid(tabela = Usuario.class, campo = "login", message = "Email duplicado")
    private String login;

    @NotBlank
    @Size(min = 6)
    private String senha;

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }

    public Usuario converter() {
        return new Usuario(login, new SenhaLimpa(senha));
    }

}
