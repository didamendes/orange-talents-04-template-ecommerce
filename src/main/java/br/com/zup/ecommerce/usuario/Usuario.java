package br.com.zup.ecommerce.usuario;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "login", nullable = false, unique = true)
    private String login;

    @Column(name = "senha", nullable = false)
    private String senha;

    @CreationTimestamp
    private OffsetDateTime dataCriacao;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Perfil> perfis = new ArrayList<>();

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(id, usuario.id) && Objects.equals(login, usuario.login) && Objects.equals(senha, usuario.senha) && Objects.equals(dataCriacao, usuario.dataCriacao) && Objects.equals(perfis, usuario.perfis);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, senha, dataCriacao, perfis);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return perfis;
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
