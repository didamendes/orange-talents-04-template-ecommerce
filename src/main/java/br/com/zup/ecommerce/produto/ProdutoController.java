package br.com.zup.ecommerce.produto;

import br.com.zup.ecommerce.categoria.Categoria;
import br.com.zup.ecommerce.categoria.CategoriaRepository;
import br.com.zup.ecommerce.produto.dto.FotoRequest;
import br.com.zup.ecommerce.produto.dto.ProdutoRequest;
import br.com.zup.ecommerce.produto.dto.ProdutoResponse;
import br.com.zup.ecommerce.produto.model.Produto;
import br.com.zup.ecommerce.produto.uploadFoto.Upload;
import br.com.zup.ecommerce.produto.uploadFoto.UploadLink;
import br.com.zup.ecommerce.usuario.Usuario;
import br.com.zup.ecommerce.validatores.ExistId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;

@Validated
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
