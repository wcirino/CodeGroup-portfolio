package com.codegroup.portfolio.controller;

import com.codegroup.portfolio.entity.MembrosEntity;
import com.codegroup.portfolio.service.MembrosService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.*;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

class MembrosControllerTest {

    @Mock
    private MembrosService membrosService;

    @InjectMocks
    private MembrosController membrosController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(membrosController).build();
    }

    @Test
    void testAssociarMembrosAoProjeto() throws Exception {

        MembrosEntity membrosEntity = new MembrosEntity(1L, 2L);
        mockMvc.perform(MockMvcRequestBuilders.post("/api-membros/associar-membro-projeto")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"idProjeto\":1,\"idPessoa\":2}"))
               .andExpect(MockMvcResultMatchers.status().isCreated())
               .andExpect(MockMvcResultMatchers.content().string("Associação realizada com sucesso"));

        verify(membrosService, times(1)).associarMembrosAoProjeto(any(MembrosEntity.class));
        verifyNoMoreInteractions(membrosService);
    }
}
