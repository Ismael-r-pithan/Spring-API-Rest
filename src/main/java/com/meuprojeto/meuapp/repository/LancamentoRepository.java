package com.meuprojeto.meuapp.repository;

import com.meuprojeto.meuapp.model.Lancamento;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LancamentoRepository extends JpaRepository<Lancamento, Long>{
    
}
