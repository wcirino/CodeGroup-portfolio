package com.codegroup.portfolio.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.codegroup.portfolio.entity.PessoaEntity;
import com.codegroup.portfolio.entity.ProjetoEntity;
import com.codegroup.portfolio.exception.ResourceBadRequestException;
import com.codegroup.portfolio.repository.PessoaRepository;
import com.codegroup.portfolio.repository.ProjetoRepository;

@ExtendWith(MockitoExtension.class)
class ProjetoServiceTest {

	@Mock
	private PessoaRepository pessoaRepository;  
	 
    @Mock
    private ProjetoRepository projetoRepository;

    @Mock
    private PessoaService pessoaService;

    @InjectMocks
    private ProjetoService projetoService;

    @Test
    void testFindProjetosByCriteria() {

    }

    @Test
    void testFindAllProjetos() {

        List<ProjetoEntity> projetos = Arrays.asList(new ProjetoEntity(), new ProjetoEntity());
        when(projetoRepository.findAll()).thenReturn(projetos);
        List<ProjetoEntity> result = projetoService.findAllProjetos();
        assertEquals(2, result.size());
    }

    @Test
    void testFindProjetosByStatus() {
        String status = "Em Andamento";
        List<ProjetoEntity> projetos = Arrays.asList(new ProjetoEntity(), new ProjetoEntity());
        when(projetoRepository.findByStatus(status)).thenReturn(projetos);

        List<ProjetoEntity> result = projetoService.findProjetosByStatus(status);
        assertEquals(2, result.size());
    }

    @Test
    void testValidarInformacoesProjeto() {
       
    	ProjetoEntity projeto = new ProjetoEntity();
        projeto.setRisco("baixo");
        projeto.setStatus("em andamento");

        assertDoesNotThrow(() -> projetoService.validarInformacoesProjeto(projeto));
    }


    @Test
    void testValidarInformacoesProjeto_InvalidStatus() {
        ProjetoEntity projeto = new ProjetoEntity();
        projeto.setStatus("invalido");
        NullPointerException exception = assertThrows(NullPointerException.class, () -> projetoService.validarInformacoesProjeto(projeto));
        assertNotNull(exception);
    }


    @Test
    void testExcluirProjeto_InvalidStatus() {

        ProjetoEntity projeto = new ProjetoEntity();
        projeto.setId(1L);
        projeto.setStatus("iniciado");
   }
}
       
