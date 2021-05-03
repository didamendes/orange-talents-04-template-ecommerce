package br.com.zup.ecommerce.produto;

import br.com.zup.ecommerce.categoria.Categoria;
import br.com.zup.ecommerce.categoria.CategoriaRepository;
import br.com.zup.ecommerce.produto.dto.ProdutoRequest;
import br.com.zup.ecommerce.produto.dto.ProdutoResponse;
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
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @PostMapping
    public ResponseEntity<ProdutoResponse> salvar(@Valid @RequestBody ProdutoRequest produtoRequest, @AuthenticationPrincipal Usuario usuario) {
        Categoria categoria = categoriaRepository.findById(produtoRequest.getIdCategoria()).get();
        Produto produto = produtoRequest.converter(usuario, categoria);
        produtoRepository.save(produto);

        return ResponseEntity.ok(new ProdutoResponse(produto));
    }

}
