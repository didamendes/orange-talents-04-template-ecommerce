package br.com.zup.ecommerce.categoria;

import br.com.zup.ecommerce.validatores.ExistId;
import br.com.zup.ecommerce.validatores.UniqueValid;

import javax.validation.constraints.NotBlank;

public class CategoriaRequest {

    @NotBlank
    @UniqueValid(tabela = Categoria.class, campo = "nome", message = "Categoria ja existente")
    private String nome;

    @ExistId(tabela = Categoria.class, campo = "id", message = "Categoria não encontrada")
    private Long idCategoriaMae;

    public String getNome() {
        return nome;
    }

    public Long getIdCategoriaMae() {
        return idCategoriaMae;
    }

    public Categoria converter(Categoria categoriaMae) {
        return new Categoria(nome, categoriaMae);
    }

}
