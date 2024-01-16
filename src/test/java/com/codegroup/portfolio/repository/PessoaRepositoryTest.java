package com.codegroup.portfolio.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.codegroup.portfolio.entity.PessoaEntity;

@SpringBootTest
@Transactional
public class PessoaRepositoryTest {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Test
    public void testFindByNome() {

        String nome = "João Silva";
        List<PessoaEntity> pessoas = pessoaRepository.findByNome(nome);
        assertNotNull(pessoas);
        assertEquals(1, pessoas.size());
        assertEquals(nome, pessoas.get(0).getNome());
    }

    @Test
    public void testGetIdFuncionario() {

        Long id = 1L;
        PessoaEntity pessoa = pessoaRepository.getIdFuncionario(id);

        assertNotNull(pessoa);
        assertEquals(id, pessoa.getId());
    }
}

