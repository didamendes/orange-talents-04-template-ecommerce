package br.com.zup.ecommerce.produto.pergunta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import br.com.zup.ecommerce.produto.ProdutoRepository;
import br.com.zup.ecommerce.produto.email.Email;
import br.com.zup.ecommerce.produto.email.EmailFaker;
import br.com.zup.ecommerce.produto.model.Produto;
import br.com.zup.ecommerce.usuario.Usuario;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/perguntas")
public class PerguntaController {

	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private PerguntaRepository perguntaRepository;
	
	private Email email = new EmailFaker();

    @PostMapping
    public ResponseEntity<?> salvar(@Valid @RequestBody PerguntaRequest perguntaRequest,
                                    @AuthenticationPrincipal Usuario usuario) {
    	Produto produto = produtoRepository.findById(perguntaRequest.getIdProduto()).get();
        Pergunta pergunta = perguntaRequest.converter(usuario, produto);
        perguntaRepository.save(pergunta);
        email.enviarEmail(pergunta);
        
        return ResponseEntity.ok(new PerguntaResponse(pergunta));
    }

}
