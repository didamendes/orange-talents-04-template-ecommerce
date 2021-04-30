package br.com.zup.ecommerce.categoria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    private Categoria categoriaMae = null;
    private CategoriaRepository categoriaRepository;

    @Autowired
    public CategoriaController(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @PostMapping
    public ResponseEntity<CategoriaResponse> salvar(@Valid @RequestBody CategoriaRequest categoriaRequest) {
        if (categoriaRequest.getIdCategoriaMae() != null) {
            categoriaMae = categoriaRepository.findById(categoriaRequest.getIdCategoriaMae()).get();
        }

        Categoria categoria = categoriaRequest.converter(categoriaMae);
        categoriaRepository.save(categoria);

        return ResponseEntity.ok(new CategoriaResponse(categoria));
    }

}
