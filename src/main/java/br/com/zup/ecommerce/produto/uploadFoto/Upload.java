package br.com.zup.ecommerce.produto.uploadFoto;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;

public interface Upload {

    Set<String> enviar(List<MultipartFile> fotos);

}
