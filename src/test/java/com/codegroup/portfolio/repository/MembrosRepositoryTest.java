package com.codegroup.portfolio.repository;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;

import jakarta.transaction.Transactional;

public class MembrosRepositoryTest {

	
    @Test
    @Transactional
    void testDeleteByProjetoIdAndPessoaId() {

        MembrosRepository membrosRepository = mock(MembrosRepository.class);
        membrosRepository.deleteByProjetoIdAndPessoaId(1L);
        verify(membrosRepository, times(1)).deleteByProjetoIdAndPessoaId(1L);
    }

    @Test
    @Transactional
    void testInsertMembro() {

        MembrosRepository membrosRepository = mock(MembrosRepository.class);
        membrosRepository.insertMembro(1L, 2L);
        verify(membrosRepository, times(1)).insertMembro(1L, 2L);
    }
}
