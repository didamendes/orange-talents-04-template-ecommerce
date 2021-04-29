package br.com.zup.ecommerce.usuario;

import java.time.OffsetDateTime;

public class UsuarioResponse {

    private Long id;

    private String login;

    private OffsetDateTime dataCriacao;

    public UsuarioResponse(Usuario usuario) {
        this.id = usuario.getId();
        this.login = usuario.getLogin();
        this.dataCriacao = usuario.getDataCriacao();
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
