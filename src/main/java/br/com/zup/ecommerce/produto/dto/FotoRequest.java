package br.com.zup.ecommerce.produto.dto;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

public class FotoRequest {

    @Size(min = 1)
    @NotNull
    private List<MultipartFile> fotos = new ArrayList<>();

    @Deprecated
    public FotoRequest() {}

    public FotoRequest(List<MultipartFile> fotos) {
        this.fotos = fotos;
    }

    public List<MultipartFile> getFotos() {
        return fotos;
    }
}
