package br.com.zup.ecommerce.produto.pergunta;

import java.time.OffsetDateTime;

public class PerguntaResponse {

	private Long id;

	private String titulo;

	private OffsetDateTime dataCriacao;

	private String produto;

	private String usuario;

	public PerguntaResponse(Pergunta pergunta) {
		this.id = pergunta.getId();
		this.titulo = pergunta.getTitulo();
		this.dataCriacao = pergunta.getDataCriacao();
		this.produto = pergunta.getProduto().getNome();
		this.usuario = pergunta.getUsuario().getLogin();
	}

	public Long getId() {
		return id;
	}

	public String getTitulo() {
		return titulo;
	}

	public OffsetDateTime getDataCriacao() {
		return dataCriacao;
	}

	public String getCategoria() {
		return produto;
	}

	public String getUsuario() {
		return usuario;
	}

}
