package br.com.zup.ecommerce.produto.opiniao;

import br.com.zup.ecommerce.produto.ProdutoRepository;
import br.com.zup.ecommerce.produto.model.Produto;
import br.com.zup.ecommerce.usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/opinioes")
public class OpiniaoController {

    @Autowired
    private OpiniaoRepository opiniaoRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @PostMapping
    public ResponseEntity<OpiniaoResponse> salvar(@Valid @RequestBody OpiniaoRequest opiniaoRequest,
                                                  @AuthenticationPrincipal Usuario usuario) {
        Produto produto = produtoRepository.findById(opiniaoRequest.getIdProduto()).get();
        Opiniao opiniao = opiniaoRequest.converter(usuario, produto);

        opiniaoRepository.save(opiniao);

        return ResponseEntity.ok(new OpiniaoResponse(opiniao));
    }

}
