package br.com.zup.ecommerce.produto;

import br.com.zup.ecommerce.produto.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
