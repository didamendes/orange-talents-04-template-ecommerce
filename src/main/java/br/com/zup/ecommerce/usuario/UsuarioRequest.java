package br.com.zup.ecommerce.usuario;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class UsuarioRequest {

    @NotBlank
    @Email
    private String login;

    @NotBlank
    @Min(6)
    private String senha;

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }

    public Usuario converter() {
        String senhaEncode = new BCryptPasswordEncoder().encode(senha);
        return new Usuario(login, senhaEncode);
    }

}
