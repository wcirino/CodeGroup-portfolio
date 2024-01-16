package com.codegroup.portfolio.repository;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.codegroup.portfolio.entity.ProjetoEntity;

@Repository
public interface ProjetoRepository extends JpaRepository<ProjetoEntity, Long> {

    List<ProjetoEntity> findAll();
    
    List<ProjetoEntity> findAll(Specification<ProjetoEntity> spec);

    List<ProjetoEntity> findByStatus(String status);
    
    ProjetoEntity save(float id);
}

