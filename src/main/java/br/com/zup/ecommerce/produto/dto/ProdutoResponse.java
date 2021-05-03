package br.com.zup.ecommerce.produto.dto;

import br.com.zup.ecommerce.categoria.CategoriaResponse;
import br.com.zup.ecommerce.produto.model.Produto;
import br.com.zup.ecommerce.usuario.UsuarioResponse;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProdutoResponse {

    private Long id;

    private String nome;

    private String descricao;

    private BigDecimal valor;

    private Integer quantidade;

    private UsuarioResponse usuario;

    private CategoriaResponse categoria;

    private List<CaracteristicaResponse> caracteristicas;

    public ProdutoResponse(Produto produto) {
        this.id = produto.getId();
        this.nome = produto.getNome();
        this.descricao = produto.getDescricao();
        this.valor = produto.getValor();
        this.quantidade = produto.getQuantidade();
        this.usuario = new UsuarioResponse(produto.getUsuario());
        this.categoria = new CategoriaResponse(produto.getCategoria());
        this.caracteristicas = new ArrayList<>();
        List<CaracteristicaResponse> cars = produto.getCaracteristicas().stream().map(car -> new CaracteristicaResponse(car)).collect(Collectors.toList());
        this.caracteristicas.addAll(cars);
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

    public UsuarioResponse getUsuario() {
        return usuario;
    }

    public CategoriaResponse getCategoria() {
        return categoria;
    }

    public List<CaracteristicaResponse> getCaracteristicas() {
        return caracteristicas;
    }
}
