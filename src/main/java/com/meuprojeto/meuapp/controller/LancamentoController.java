package com.meuprojeto.meuapp.controller;

import java.util.List;
import java.util.Optional;

import com.meuprojeto.meuapp.model.Lancamento;
import com.meuprojeto.meuapp.repository.LancamentoRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/lancamentos")
@AllArgsConstructor
public class LancamentoController {
    
    private LancamentoRepository lancamentoRepository;

    @GetMapping
    public ResponseEntity<?> buscarTodos() {

        List<Lancamento> lancamentos = lancamentoRepository.findAll();

        return !lancamentos.isEmpty() ? ResponseEntity.ok(lancamentos) : ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId (@PathVariable Long id) {

        Optional<Lancamento> lancamento = lancamentoRepository.findById(id);

        return lancamento.isPresent() ? ResponseEntity.ok(lancamento.get()) : ResponseEntity.noContent().build();
        
    }

}
