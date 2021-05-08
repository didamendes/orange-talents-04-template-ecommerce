package br.com.zup.ecommerce.produto.pergunta;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.zup.ecommerce.produto.model.Produto;
import br.com.zup.ecommerce.usuario.Usuario;
import br.com.zup.ecommerce.validatores.ExistId;

public class PerguntaRequest {

	@NotBlank
	private String titulo;
	
	@NotNull
	@ExistId(tabela = Produto.class, campo = "id", message = "Produto não existe")
	private Long idProduto;

	public String getTitulo() {
		return titulo;
	}

	public Long getIdProduto() {
		return idProduto;
	}
	
	public Pergunta converter(Usuario usuario, Produto produto) {
		return new Pergunta(titulo, usuario, produto);
	}
	
}
