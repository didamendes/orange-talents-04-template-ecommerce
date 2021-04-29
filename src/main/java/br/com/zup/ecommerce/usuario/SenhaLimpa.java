package br.com.zup.ecommerce.usuario;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class SenhaLimpa {

    private String senha;

    public SenhaLimpa(String senha) {
        this.senha = senha;
    }

    public String toHash() {
        return new BCryptPasswordEncoder().encode(senha);
    }
}
