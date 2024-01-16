package com.codegroup.portfolio.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.codegroup.portfolio.entity.PessoaEntity;
import com.codegroup.portfolio.repository.PessoaRepository;

@SpringBootTest
public class PessoaServiceTest {

    @Mock
    private PessoaRepository pessoaRepository;

    @InjectMocks
    private PessoaService pessoaService;

    @Test
    public void testIsFuncionario() {
        Long pessoaId = 1L;
        PessoaEntity pessoa = new PessoaEntity();
        pessoa.setId(pessoaId);
        pessoa.setFuncionario(true);

        when(pessoaRepository.getIdFuncionario(pessoaId)).thenReturn(pessoa);

        boolean isFuncionario = pessoaService.isFuncionario(pessoaId);

        assertTrue(isFuncionario);
        verify(pessoaRepository, times(1)).getIdFuncionario(pessoaId);
    }
    
    @Test
    public void testIsNotFuncionario() {
        Long pessoaId = 2L;
        PessoaEntity pessoa = new PessoaEntity();
        pessoa.setId(pessoaId);
        pessoa.setFuncionario(false);

        when(pessoaRepository.getIdFuncionario(pessoaId)).thenReturn(pessoa);
        boolean isFuncionario = pessoaService.isFuncionario(pessoaId);

        assertFalse(isFuncionario);
        verify(pessoaRepository, times(1)).getIdFuncionario(pessoaId);
    }

    @Test
    public void testPessoaNotFound() {
    	
        Long pessoaId = 3L;
        when(pessoaRepository.getIdFuncionario(pessoaId)).thenReturn(null);
        boolean isFuncionario = pessoaService.isFuncionario(pessoaId);

        assertFalse(isFuncionario);
        verify(pessoaRepository, times(1)).getIdFuncionario(pessoaId);
    }
    
    @Test
    public void testExceptionHandling() {
        Long pessoaId = 4L;

        when(pessoaRepository.getIdFuncionario(pessoaId)).thenThrow(new RuntimeException("Erro simulado"));
        assertThrows(RuntimeException.class, () -> pessoaService.isFuncionario(pessoaId));
        verify(pessoaRepository, times(1)).getIdFuncionario(pessoaId);
    }


}
