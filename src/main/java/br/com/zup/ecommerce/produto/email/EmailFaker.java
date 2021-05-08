package br.com.zup.ecommerce.produto.email;

import br.com.zup.ecommerce.produto.pergunta.Pergunta;

public class EmailFaker implements Email {

	@Override
	public void enviarEmail(Pergunta pergunta) {
		System.out.println("Usuario: " + pergunta.getUsuario().getLogin() + " enviou uma pergunta.");
		System.out.println(pergunta.getTitulo());
	}

}
