package br.com.zup.ecommerce.produto;

import java.util.List;
import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.zup.ecommerce.categoria.Categoria;
import br.com.zup.ecommerce.categoria.CategoriaRepository;
import br.com.zup.ecommerce.produto.dto.DetalharProdutoResponse;
import br.com.zup.ecommerce.produto.dto.ProdutoRequest;
import br.com.zup.ecommerce.produto.dto.ProdutoResponse;
import br.com.zup.ecommerce.produto.model.Produto;
import br.com.zup.ecommerce.produto.uploadFoto.Upload;
import br.com.zup.ecommerce.produto.uploadFoto.UploadLink;
import br.com.zup.ecommerce.usuario.Usuario;
import br.com.zup.ecommerce.validatores.ExistId;

@Validated
@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private ProdutoRepository produtoRepository;
    
    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getById(@ExistId(tabela = Produto.class, campo = "id", message = "Produto inexiste") @PathVariable Long id) {
    	Produto produto = produtoRepository.findById(id).get();
    	return ResponseEntity.ok(new DetalharProdutoResponse(produto));
    }

    @PostMapping
    public ResponseEntity<ProdutoResponse> salvar(@Valid @RequestBody ProdutoRequest produtoRequest, @AuthenticationPrincipal Usuario usuario) {
        Categoria categoria = categoriaRepository.findById(produtoRequest.getIdCategoria()).get();
        Produto produto = produtoRequest.converter(usuario, categoria);
        produtoRepository.save(produto);

        return ResponseEntity.ok(new ProdutoResponse(produto));
    }

    @PutMapping(path = "/{id}/foto")
    public ResponseEntity<?> uploadfoto(@ExistId(tabela = Produto.class, campo = "id", message = "Produto inexiste") @PathVariable Long id,
                                        @NotNull @Size(min = 1) List<MultipartFile> fotos,
                                        @AuthenticationPrincipal Usuario usuario) {
        Produto produto = produtoRepository.findById(id).get();

        if (!produto.isPertenceUsuario(usuario)) {
            return ResponseEntity.status(403).build();
        }

        Upload upload = new UploadLink();
        Set<String> fotosLink = upload.enviar(fotos);
        produto.adicionarImagem(fotosLink);
        produtoRepository.save(produto);

        return ResponseEntity.ok().build();
    }

}
