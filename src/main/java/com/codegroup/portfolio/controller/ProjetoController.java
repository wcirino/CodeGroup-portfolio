package com.codegroup.portfolio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codegroup.portfolio.entity.ProjetoEntity;
import com.codegroup.portfolio.exception.ResourceBadRequestException;
import com.codegroup.portfolio.service.ProjetoService;

@RestController
@RequestMapping("/api-projetos")
public class ProjetoController {

    @Autowired
    private ProjetoService projetoService;

    @GetMapping("/findAll")
    public ResponseEntity<?> findAllProjetos() {
        List<ProjetoEntity> projetos = projetoService.findAllProjetos();

        if (projetos == null) {
            throw new ResourceBadRequestException("A consulta retornou um valor nulo");
        }

        return new ResponseEntity<>(projetos, HttpStatus.OK);
    }

    
    @GetMapping("/find-all")
    public ResponseEntity<?>  find_all(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String risco) {
    	
        List<ProjetoEntity> projetos = projetoService.findProjetosByCriteria(nome, status, risco);

        if (projetos == null) {
            throw new ResourceBadRequestException("A consulta retornou um valor nulo");
        }

        return new ResponseEntity<>(projetos, HttpStatus.OK);
    }
    
    @PostMapping("/")
    public ResponseEntity<?> criarProjeto(@RequestBody ProjetoEntity p) {

            ProjetoEntity projeto = projetoService.saveProjeto(p);
            return new ResponseEntity<>(projeto, HttpStatus.CREATED);
   
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarProjeto(@PathVariable Long id, @RequestBody ProjetoEntity p) {
    	    	p.setId(id);
    	        ProjetoEntity projetoAtualizado = projetoService.saveProjeto(p);
    	        return new ResponseEntity<>(projetoAtualizado, HttpStatus.OK);
         

    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluirProjeto(@PathVariable Long id) {
        try {
            projetoService.excluirProjeto(id);
            return new ResponseEntity<>("Projeto excluído com sucesso", HttpStatus.OK);
        } catch (ResourceBadRequestException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception ex) {
            return new ResponseEntity<>("Ocorreu um erro no sistema", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
