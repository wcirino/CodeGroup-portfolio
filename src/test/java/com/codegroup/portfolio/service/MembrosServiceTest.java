package com.codegroup.portfolio.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import com.codegroup.portfolio.entity.MembrosEntity;
import com.codegroup.portfolio.entity.PessoaEntity;
import com.codegroup.portfolio.entity.ProjetoEntity;
import com.codegroup.portfolio.exception.ResourceBadRequestException;
import com.codegroup.portfolio.exception.ResourceNotFoundException;
import com.codegroup.portfolio.repository.MembrosRepository;
import com.codegroup.portfolio.repository.PessoaRepository;
import com.codegroup.portfolio.repository.ProjetoRepository;
import com.codegroup.portfolio.service.MembrosService;
import com.codegroup.portfolio.service.PessoaService;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

@MockitoSettings(strictness = Strictness.LENIENT)
public class MembrosServiceTest {

    @Mock
    private ProjetoRepository projetoRepository;

    @Mock
    private PessoaRepository pessoaRepository;

    @Mock
    private MembrosRepository membrosRepository;

    @Mock
    private PessoaService pessoaService;

    @InjectMocks
    private MembrosService membrosService;

    public MembrosServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAssociarMembrosAoProjeto() {

        MembrosEntity membrosEntity = new MembrosEntity(1L, 2L);

        ProjetoEntity projeto = new ProjetoEntity();
        projeto.setId(1L);

        PessoaEntity pessoa = new PessoaEntity();
        pessoa.setId(2L);

        when(projetoRepository.findById(1L)).thenReturn(Optional.of(projeto));
        when(pessoaRepository.findById(2L)).thenReturn(Optional.of(pessoa));
        when(pessoaService.isFuncionario(2L)).thenReturn(true);

        membrosService.associarMembrosAoProjeto(membrosEntity);

        verify(projetoRepository, times(1)).findById(1L);
        verify(pessoaRepository, times(1)).findById(2L);
        verify(pessoaService, times(1)).isFuncionario(2L);
        verify(membrosRepository, times(1)).insertMembro(1L, 2L);
    }

    @Test
    public void testAssociarMembrosAoProjetoProjetoNaoEncontrado() {

        MembrosEntity membrosEntity = new MembrosEntity(1L, 2L);
        when(projetoRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> membrosService.associarMembrosAoProjeto(membrosEntity));
    }

    @Test
    public void testAssociarMembrosAoProjetoPessoaNaoEncontrada() {
        MembrosEntity membrosEntity = new MembrosEntity(1L, 2L);

        ProjetoEntity projeto = new ProjetoEntity();
        projeto.setId(1L);

        when(projetoRepository.findById(1L)).thenReturn(Optional.of(projeto));
        when(pessoaRepository.findById(2L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> membrosService.associarMembrosAoProjeto(membrosEntity));
    }

    @Test
    public void testAssociarMembrosAoProjetoNaoFuncionario() {

        MembrosEntity membrosEntity = new MembrosEntity(1L, 2L);

        ProjetoEntity projeto = new ProjetoEntity();
        projeto.setId(1L);

        PessoaEntity pessoa = new PessoaEntity();
        pessoa.setId(2L);

        when(projetoRepository.findById(1L)).thenReturn(Optional.of(projeto));
        when(pessoaRepository.findById(2L)).thenReturn(Optional.of(pessoa));
        when(pessoaService.isFuncionario(2L)).thenReturn(false);
        assertThrows(ResourceBadRequestException.class, () -> membrosService.associarMembrosAoProjeto(membrosEntity));
    }
}
