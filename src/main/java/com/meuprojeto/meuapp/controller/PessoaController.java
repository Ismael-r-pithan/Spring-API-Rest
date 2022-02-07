package com.meuprojeto.meuapp.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.meuprojeto.meuapp.model.Pessoa;
import com.meuprojeto.meuapp.repository.PessoaRepository;

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
@AllArgsConstructor
@RequestMapping("/pessoas")
public class PessoaController {
    
    private PessoaRepository pessoaRepository;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void adicionar (@Valid @RequestBody Pessoa pessoa, HttpServletResponse response) {
        Pessoa pessoaAdicionada = pessoaRepository.save(pessoa);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(pessoaAdicionada.getId()).toUri();

        response.setHeader("Location", uri.toASCIIString());

    }

    // poderia ser para classe, ou para aplicação inteira
    @CrossOrigin(maxAge = 10 ,origins = {"http://localhost:4200"})
    @GetMapping
    public ResponseEntity<?> buscarTodas() {

        List<Pessoa> pessoas = pessoaRepository.findAll();

        if (!pessoas.isEmpty()) {
            return ResponseEntity.ok(pessoas);
        }

        return ResponseEntity.noContent().build();

    }


    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        Optional<Pessoa> pessoa = pessoaRepository.findById(id);

        if (!pessoa.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        
        return ResponseEntity.ok(pessoa.get());
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removendoPorId(@PathVariable Long id) {
        pessoaRepository.deleteById(id);
    }

}
