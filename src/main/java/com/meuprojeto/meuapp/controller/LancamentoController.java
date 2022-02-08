package com.meuprojeto.meuapp.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import com.meuprojeto.meuapp.model.Categoria;
import com.meuprojeto.meuapp.model.Lancamento;
import com.meuprojeto.meuapp.repository.LancamentoRepository;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/lancamentos")
@AllArgsConstructor
public class LancamentoController {
    
    private LancamentoRepository lancamentoRepository;


    @GetMapping
    @CrossOrigin(maxAge = 10, origins = { "http://localhost:4200" })
    public ResponseEntity<?> buscarTodos() {

        List<Lancamento> lancamentos = lancamentoRepository.findAll();

        return !lancamentos.isEmpty() ? ResponseEntity.ok(lancamentos) : ResponseEntity.noContent().build();
    }

    @CrossOrigin(maxAge = 10, origins = { "http://localhost:4200" })
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId (@PathVariable Long id) {

        Optional<Lancamento> lancamento = lancamentoRepository.findById(id);

        return lancamento.isPresent() ? ResponseEntity.ok(lancamento.get()) : ResponseEntity.noContent().build();

    }

    @CrossOrigin(maxAge = 10, origins = { "http://localhost:4200" })
    @DeleteMapping("/{id}")
    public void deleterPorId(@PathVariable Long id) {
       lancamentoRepository.deleteById(id);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void adicionar(@RequestBody Lancamento lancamento, HttpServletResponse response) {
        Lancamento lancamentoAdicionado = lancamentoRepository.save(lancamento);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(lancamentoAdicionado.getId()).toUri();

        response.setHeader("Location", uri.toASCIIString());

    }

}
