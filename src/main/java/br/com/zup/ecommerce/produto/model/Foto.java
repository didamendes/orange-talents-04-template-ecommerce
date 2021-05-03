package br.com.zup.ecommerce.produto.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Foto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "url", nullable = false)
    private String url;

    @ManyToOne
    @JoinColumn(name = "produto_id", nullable = false)
    private Produto produto;

    @Deprecated
    public Foto() {}

    public Foto(String url, Produto produto) {
        this.url = url;
        this.produto = produto;
    }

    public Long getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public Produto getProduto() {
        return produto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Foto foto = (Foto) o;
        return Objects.equals(id, foto.id) && Objects.equals(url, foto.url) && Objects.equals(produto, foto.produto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, url, produto);
    }
}
