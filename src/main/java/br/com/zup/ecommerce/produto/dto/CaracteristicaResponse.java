package br.com.zup.ecommerce.produto.dto;

import br.com.zup.ecommerce.produto.model.Caracteristica;

public class CaracteristicaResponse {

    private Long id;

    private String nome;

    private String descricao;

    public CaracteristicaResponse(Caracteristica caracteristica) {
        this.id = caracteristica.getId();
        this.nome = caracteristica.getNome();
        this.descricao = caracteristica.getDescricao();
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
}
