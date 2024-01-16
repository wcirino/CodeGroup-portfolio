package com.codegroup.portfolio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codegroup.portfolio.entity.MembrosEntity;
import com.codegroup.portfolio.entity.PessoaEntity;
import com.codegroup.portfolio.entity.ProjetoEntity;
import com.codegroup.portfolio.exception.ResourceBadRequestException;
import com.codegroup.portfolio.exception.ResourceNotFoundException;
import com.codegroup.portfolio.repository.MembrosRepository;
import com.codegroup.portfolio.repository.PessoaRepository;
import com.codegroup.portfolio.repository.ProjetoRepository;

@Service
public class MembrosService {

    @Autowired
    private ProjetoRepository projetoRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private MembrosRepository membrosRepository;
    
	@Autowired
	private PessoaService pessoaService;

    public void associarMembrosAoProjeto(MembrosEntity membrosEntity) {
        ProjetoEntity projeto = projetoRepository.findById(membrosEntity.getIdProjeto())
                .orElseThrow(() -> new ResourceNotFoundException("Projeto não encontrado"));

        PessoaEntity pessoa = pessoaRepository.findById(membrosEntity.getIdPessoa())
                .orElseThrow(() -> new ResourceNotFoundException("Pessoa não encontrada"));
        
        validarGerenteFuncionario(membrosEntity.getIdPessoa(), "Permitido apenas inserir funcionário");
        
        membrosRepository.insertMembro(projeto.getId(),pessoa.getId());
    }
    

	private void validarGerenteFuncionario(Long gerenteId, String mensagem) {
		if (!pessoaService.isFuncionario(gerenteId)) {
			throw new ResourceBadRequestException(mensagem);
		}
	}
}
