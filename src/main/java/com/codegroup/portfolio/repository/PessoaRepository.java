package com.codegroup.portfolio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.codegroup.portfolio.entity.PessoaEntity;

@Repository
public interface PessoaRepository extends JpaRepository<PessoaEntity, Long> {
    List<PessoaEntity> findByNome(String nome);
    
    @Query("SELECT p FROM PessoaEntity p WHERE p.id = :id")
    PessoaEntity getIdFuncionario(@Param("id") Long id);
}

