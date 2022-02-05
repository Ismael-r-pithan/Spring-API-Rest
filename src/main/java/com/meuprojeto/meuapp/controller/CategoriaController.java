package com.meuprojeto.meuapp.controller;

import java.util.List;

import com.meuprojeto.meuapp.model.Categoria;
import com.meuprojeto.meuapp.repository.CategoriaRepository;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    private CategoriaRepository categoriaRepository;
    
    @GetMapping
    public List<Categoria> buscarTodas() {
        return categoriaRepository.findAll();
    }
}
