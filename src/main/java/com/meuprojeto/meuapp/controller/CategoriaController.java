package com.meuprojeto.meuapp.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import com.meuprojeto.meuapp.model.Categoria;
import com.meuprojeto.meuapp.repository.CategoriaRepository;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    private CategoriaRepository categoriaRepository;
    
    @GetMapping
    public ResponseEntity<?> buscarTodas () {
        List<Categoria> categorias = categoriaRepository.findAll();

        return !categorias.isEmpty() ? ResponseEntity.ok(categorias) : ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId (@PathVariable Long id) {
        Optional<Categoria> categoria = categoriaRepository.findById(id);

        if (categoria.isPresent()) {
            return ResponseEntity.ok(categoria);
        }

        return ResponseEntity.notFound().build();
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void adicionar (@RequestBody Categoria categoria, HttpServletResponse response) {
        Categoria categoriaAdicionada = categoriaRepository.save(categoria);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
            .buildAndExpand(categoriaAdicionada.getId()).toUri();

        response.setHeader("Location", uri.toASCIIString());
    }


    

}
