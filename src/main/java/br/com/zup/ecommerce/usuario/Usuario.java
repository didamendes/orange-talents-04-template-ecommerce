package br.com.zup.ecommerce.usuario;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "login", nullable = false, unique = true)
    private String login;

    @Column(name = "senha", nullable = false)
    private String senha;

    @CreationTimestamp
    private OffsetDateTime dataCriacao;

    public Usuario() {
    }

    public Usuario(String login, SenhaLimpa senhaLimpa) {
        this.login = login;
        this.senha = senhaLimpa.toHash();
    }

    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public OffsetDateTime getDataCriacao() {
        return dataCriacao;
    }
}
