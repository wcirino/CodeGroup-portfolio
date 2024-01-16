package com.codegroup.portfolio.controller;

import com.codegroup.portfolio.entity.ProjetoEntity;
import com.codegroup.portfolio.service.ProjetoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


class ProjetoControllerTest {

    @Mock
    private ProjetoService projetoService;

    @InjectMocks
    private ProjetoController projetoController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(projetoController).build();
    }

    @Test
    void testFindAllProjetos() throws Exception {
        ProjetoEntity projeto1 = new ProjetoEntity();
        projeto1.setId(1L);
        projeto1.setNome("Projeto 1");

        ProjetoEntity projeto2 = new ProjetoEntity();
        projeto2.setId(2L);
        projeto2.setNome("Projeto 2");

        List<ProjetoEntity> projetos = Arrays.asList(projeto1, projeto2);

        when(projetoService.findAllProjetos()).thenReturn(projetos);

        mockMvc.perform(MockMvcRequestBuilders.get("/api-projetos/findAll"))
               .andExpect(MockMvcResultMatchers.status().isOk());

        verify(projetoService, times(1)).findAllProjetos();
        verifyNoMoreInteractions(projetoService);
    }

    @Test
    void testFindProjetosByCriteria() throws Exception {
        // Mockando o serviço para retornar uma lista de projetos
        ProjetoEntity projeto1 = new ProjetoEntity();
        projeto1.setId(1L);
        projeto1.setNome("Projeto 1");

        ProjetoEntity projeto2 = new ProjetoEntity();
        projeto2.setId(2L);
        projeto2.setNome("Projeto 2");

        List<ProjetoEntity> projetos = Arrays.asList(projeto1, projeto2);

        when(projetoService.findProjetosByCriteria("Projeto", "Em Andamento", "Alto")).thenReturn(projetos);

        mockMvc.perform(MockMvcRequestBuilders.get("/api-projetos/find-all")
                .param("nome", "Projeto")
                .param("status", "Em Andamento")
                .param("risco", "Alto"))
               .andExpect(MockMvcResultMatchers.status().isOk())
               .andExpect(jsonPath("$.size()").value(2))
               .andExpect(jsonPath("$[0].id").value(1))
               .andExpect(jsonPath("$[0].nome").value("Projeto 1"))
               .andExpect(jsonPath("$[1].id").value(2))
               .andExpect(jsonPath("$[1].nome").value("Projeto 2"));

        verify(projetoService, times(1)).findProjetosByCriteria("Projeto", "Em Andamento", "Alto");
        verifyNoMoreInteractions(projetoService);
    }

    @Test
    void testCriarProjeto() throws Exception {
        ProjetoEntity projeto = new ProjetoEntity();
        projeto.setId(1L);
        projeto.setNome("Novo Projeto");

        when(projetoService.saveProjeto(any(ProjetoEntity.class))).thenReturn(projeto);

        mockMvc.perform(MockMvcRequestBuilders.post("/api-projetos/")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"nome\":\"Novo Projeto\"}"))
               .andExpect(MockMvcResultMatchers.status().isCreated())
               .andExpect(jsonPath("$.id").value(1))
               .andExpect(jsonPath("$.nome").value("Novo Projeto"));

        verify(projetoService, times(1)).saveProjeto(any(ProjetoEntity.class));
        verifyNoMoreInteractions(projetoService);
    }

    @Test
    void testAtualizarProjeto() throws Exception {
        ProjetoEntity projeto = new ProjetoEntity();
        projeto.setId(1L);
        projeto.setNome("Projeto Atualizado");

        when(projetoService.saveProjeto(any(ProjetoEntity.class))).thenReturn(projeto);

        mockMvc.perform(MockMvcRequestBuilders.put("/api-projetos/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"nome\":\"Projeto Atualizado\"}"))
               .andExpect(MockMvcResultMatchers.status().isOk())
               .andExpect(jsonPath("$.id").value(1))
               .andExpect(jsonPath("$.nome").value("Projeto Atualizado"));

        verify(projetoService, times(1)).saveProjeto(any(ProjetoEntity.class));
        verifyNoMoreInteractions(projetoService);
    }

    @Test
    void testExcluirProjeto() throws Exception {
        doNothing().when(projetoService).excluirProjeto(1L);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api-projetos/1"))
               .andExpect(MockMvcResultMatchers.status().isOk())
               .andExpect(MockMvcResultMatchers.content().string("Projeto excluído com sucesso"));

        verify(projetoService, times(1)).excluirProjeto(1L);
        verifyNoMoreInteractions(projetoService);
    }
}

