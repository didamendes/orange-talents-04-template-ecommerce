package br.com.zup.ecommerce.categoria;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CategoriaResponse {

    private Long id;

    private String nome;

    private Long idCategoriaMae;

    private String nomeCategoriaMae;

    public CategoriaResponse(Categoria categoria) {
        this.id = categoria.getId();
        this.nome = categoria.getNome();
        this.idCategoriaMae = categoria.getCategoriaMae() != null ? categoria.getCategoriaMae().getId() : null;
        this.nomeCategoriaMae = categoria.getCategoriaMae() != null ? categoria.getCategoriaMae().getNome() : null;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Long getIdCategoriaMae() {
        return idCategoriaMae;
    }

    public String getNomeCategoriaMae() {
        return nomeCategoriaMae;
    }
}
