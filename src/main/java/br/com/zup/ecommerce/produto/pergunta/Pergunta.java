package br.com.zup.ecommerce.produto.pergunta;

import java.time.OffsetDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

import br.com.zup.ecommerce.produto.model.Produto;
import br.com.zup.ecommerce.usuario.Usuario;

@Entity
public class Pergunta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String titulo;

	@CreationTimestamp
	private OffsetDateTime dataCriacao;

	@ManyToOne
	@JoinColumn(name = "usuario_id", nullable = false)
	private Usuario usuario;

	@ManyToOne
	@JoinColumn(name = "produto_id", nullable = false)
	private Produto produto;

	@Deprecated
	public Pergunta() {
	}

	public Pergunta(String titulo, Usuario usuario, Produto produto) {
		super();
		this.titulo = titulo;
		this.usuario = usuario;
		this.produto = produto;
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

	public Usuario getUsuario() {
		return usuario;
	}

	public Produto getProduto() {
		return produto;
	}

}
