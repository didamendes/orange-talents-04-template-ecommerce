package br.com.zup.ecommerce.produto.uploadFoto;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class UploadLink implements Upload {

    @Override
    public Set<String> enviar(List<MultipartFile> fotos) {
        return fotos.stream().map(foto -> "https://www.google.com.br/imgres?imgurl="+foto.getOriginalFilename()).collect(Collectors.toSet());
    }

}
