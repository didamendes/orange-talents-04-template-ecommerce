package br.com.zup.ecommerce.usuario;

import br.com.zup.ecommerce.validatores.EmailDuplicado;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UsuarioRequest {

    @NotBlank
    @Email
    @EmailDuplicado(tabela = Usuario.class, campo = "login")
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
