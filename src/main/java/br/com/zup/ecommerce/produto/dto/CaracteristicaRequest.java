package br.com.zup.ecommerce.produto.dto;

import br.com.zup.ecommerce.categoria.CategoriaRequest;
import br.com.zup.ecommerce.produto.model.Caracteristica;
import br.com.zup.ecommerce.produto.model.Produto;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class CaracteristicaRequest {

    @NotBlank
    private String nome;

    @NotBlank
    private String descricao;

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public Caracteristica converter(CaracteristicaRequest caracteristicaRequest, Produto produto) {
        return new Caracteristica(caracteristicaRequest.getNome(), caracteristicaRequest.getDescricao(), produto);
    }

    public Set<Caracteristica> setCaracteristicas(List<CaracteristicaRequest> caracteristicaRequests, Produto produto) {
        return caracteristicaRequests.stream().map(caracteristicaRequest -> converter(caracteristicaRequest, produto)).collect(Collectors.toSet());
    }

}
