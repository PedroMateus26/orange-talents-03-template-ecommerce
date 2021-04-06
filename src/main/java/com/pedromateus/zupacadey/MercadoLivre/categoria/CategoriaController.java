package com.pedromateus.zupacadey.MercadoLivre.categoria;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/categories")
public class CategoriaController {

    private CategoriaRepository repository;

    public CategoriaController(CategoriaRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public ResponseEntity<CategoriaRequestDTO> inserirCategoria(@RequestBody @Valid CategoriaRequestDTO categoriaRequestDTO){
        Categoria categoria = categoriaRequestDTO.convertToModel();
        return ResponseEntity.ok().body(new CategoriaRequestDTO(repository.save(categoria).getNome()));
    }
}
