package com.codegroup.portfolio.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.codegroup.portfolio.entity.PessoaEntity;
import com.codegroup.portfolio.entity.ProjetoEntity;

@SpringBootTest
@Transactional
public class ProjetoRepositoryTest {

    @Autowired
    private ProjetoRepository projetoRepository;
    
    @Autowired
    private PessoaRepository pessoaRepository;

    @Test
    public void testFindAll() {

        List<ProjetoEntity> projetos = projetoRepository.findAll();
        assertNotNull(projetos);

    }

    @Test
    public void testFindByStatus() {
 
        String status = "Iniciado";
        List<ProjetoEntity> projetos = projetoRepository.findByStatus(status);
        assertNotNull(projetos);

    }

    @Test
    public void testSave() {

        ProjetoEntity projeto = new ProjetoEntity();
        projeto.setNome("Meu Projeto");
        projeto.setDescricao("Descrição do projeto");
        projeto.setStatus("Iniciado");
        projeto.setOrcamento(1000);
        projeto.setRisco("Baixo");

        PessoaEntity gerente = pessoaRepository.getIdFuncionario(1L);
        projeto.setGerente(gerente);

        ProjetoEntity savedProjeto = projetoRepository.save(projeto);

        assertNotNull(savedProjeto);
        assertNotNull(savedProjeto.getId());

    }

}

