package br.com.zup.ecommerce.produto.model;

import br.com.zup.ecommerce.categoria.Categoria;
import br.com.zup.ecommerce.usuario.Usuario;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome",nullable = false)
    private String nome;

    @Column(name = "descricao", nullable = false, length = 1000)
    private String descricao;

    @Column(name = "valor", nullable = false)
    private BigDecimal valor;

    @Column(name = "quantidade", nullable = false)
    private Integer quantidade;

    @CreationTimestamp
    private OffsetDateTime dataCriacao;

    @ManyToOne
    @JoinColumn(name = "categoria_id", nullable = false)
    private Categoria categoria;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @OneToMany(mappedBy = "produto", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private Set<Caracteristica> caracteristicas;

    @OneToMany(mappedBy = "produto", cascade = CascadeType.MERGE)
    private Set<Foto> fotos = new HashSet<>();

    @Deprecated
    public Produto() {}

    public Produto(String nome, String descricao, BigDecimal valor, Integer quantidade, Categoria categoria, Usuario usuario) {
        this.nome = nome;
        this.descricao = descricao;
        this.valor = valor;
        this.quantidade = quantidade;
        this.categoria = categoria;
        this.usuario = usuario;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public OffsetDateTime getDataCriacao() {
        return dataCriacao;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Set<Caracteristica> getCaracteristicas() {
        return caracteristicas;
    }

    public void setCaracteristicas(Set<Caracteristica> caracteristicas) {
        this.caracteristicas = caracteristicas;
    }

    public void adicionarImagem(Set<String> fotosLink) {
        Set<Foto> fotos = fotosLink.stream().map(link -> new Foto(link, this)).collect(Collectors.toSet());
        this.fotos.addAll(fotos);
    }

    public boolean isPertenceUsuario(Usuario usuario) {
        return this.getUsuario().getId().equals(usuario.getId());
    }
}
