package br.com.zup.ecommerce.produto.dto;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import br.com.zup.ecommerce.produto.model.Produto;
import br.com.zup.ecommerce.produto.opiniao.Opiniao;
import br.com.zup.ecommerce.produto.opiniao.OpiniaoResponse;
import br.com.zup.ecommerce.produto.pergunta.PerguntaResponse;

public class DetalharProdutoResponse {

	private Long id;
	private String nome;
	private String descricao;
	private BigDecimal valor;
	private Integer quantidade;
	private OffsetDateTime dataCricao;
	private Double media;
	private Integer total;
	private Set<String> fotos;
	private Set<CaracteristicaResponse> caracteristicas;
	private Set<OpiniaoResponse> opinioes;
	private Set<PerguntaResponse> perguntas;

	public DetalharProdutoResponse(Produto produto) {
		this.id = produto.getId();
		this.nome = produto.getNome();
		this.descricao = produto.getDescricao();
		this.valor = produto.getValor();
		this.quantidade = produto.getQuantidade();
		this.dataCricao = produto.getDataCriacao();

		this.total = produto.getOpinioes().size();
		this.media = produto.getOpinioes().stream().mapToDouble(Opiniao::getNota).average().orElse(Double.NaN);

		this.fotos = new HashSet<>();
		this.caracteristicas = new HashSet<>();
		this.caracteristicas.addAll(produto.getCaracteristicas().stream()
				.map(caracteristica -> new CaracteristicaResponse(caracteristica)).collect(Collectors.toSet()));
		this.opinioes = new HashSet();
		this.opinioes.addAll(produto.getOpinioes().stream().map(opiniao -> new OpiniaoResponse(opiniao))
				.collect(Collectors.toSet()));
		this.perguntas = new HashSet<>();
		this.perguntas.addAll(produto.getPerguntas().stream().map(pergunta -> new PerguntaResponse(pergunta))
				.collect(Collectors.toSet()));
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

	public OffsetDateTime getDataCricao() {
		return dataCricao;
	}

	public Set<String> getFotos() {
		return fotos;
	}

	public Set<OpiniaoResponse> getOpinioes() {
		return opinioes;
	}

	public Set<PerguntaResponse> getPerguntas() {
		return perguntas;
	}

	public Set<CaracteristicaResponse> getCaracteristicas() {
		return caracteristicas;
	}

	public double getMedia() {
		return media;
	}

	public Integer getTotal() {
		return total;
	}
}
