package com.meuprojeto.meuapp.repository;

import com.meuprojeto.meuapp.model.Pessoa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
    
}
