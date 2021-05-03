package br.com.zup.ecommerce.produto.dto;

import br.com.zup.ecommerce.categoria.Categoria;
import br.com.zup.ecommerce.produto.model.Caracteristica;
import br.com.zup.ecommerce.produto.model.Produto;
import br.com.zup.ecommerce.usuario.Usuario;
import br.com.zup.ecommerce.validatores.ExistId;
import br.com.zup.ecommerce.validatores.UniqueValid;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Set;

public class ProdutoRequest {

    @NotBlank
    @UniqueValid(tabela = Produto.class, campo = "nome", message = "Produto ja cadastro")
    private String nome;

    @NotBlank
    @Length(max = 1000)
    private String descricao;

    @NotNull
    @Positive
    private BigDecimal valor;

    @NotNull
    @PositiveOrZero
    private Integer quantidade;

    @NotNull
    @ExistId(tabela = Categoria.class, campo = "id", message = "Categoria inexistente")
    private Long idCategoria;

    @Size(min = 3)
    @NotNull
    @Valid
    private List<CaracteristicaRequest> caracteristicas;

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

    public Long getIdCategoria() {
        return idCategoria;
    }

    public List<CaracteristicaRequest> getCaracteristicas() {
        return caracteristicas;
    }

    public Produto converter(Usuario usuario, Categoria categoria) {
        Produto produto = new Produto(nome, descricao, valor, quantidade, categoria, usuario);
        Set<Caracteristica> caracteristicas = new CaracteristicaRequest().setCaracteristicas(this.caracteristicas, produto);
        produto.setCaracteristicas(caracteristicas);
        return produto;
    }
}
