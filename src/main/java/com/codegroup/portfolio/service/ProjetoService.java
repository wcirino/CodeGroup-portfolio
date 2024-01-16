package com.codegroup.portfolio.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.codegroup.portfolio.entity.ProjetoEntity;
import com.codegroup.portfolio.exception.ResourceBadRequestException;
import com.codegroup.portfolio.repository.MembrosRepository;
import com.codegroup.portfolio.repository.ProjetoRepository;
import com.codegroup.portfolio.specification.ProjetoSpecifications;

@Service
public class ProjetoService {
	
	@Autowired
	private ProjetoRepository projetoRepository;
	
	@Autowired
	private MembrosRepository membropository;

	@Autowired
	private PessoaService pessoaService;

	public List<ProjetoEntity> findProjetosByCriteria(String nome, String status, String risco) {
		Specification<ProjetoEntity> spec = Specification.where(ProjetoSpecifications.nomeLike(nome))
				.and(ProjetoSpecifications.statusLike(status)).and(ProjetoSpecifications.riscoEqual(risco));

		List<ProjetoEntity> lista = projetoRepository.findAll(spec);
		return lista;
	}

	public List<ProjetoEntity> findAllProjetos() {
		List<ProjetoEntity> lista = projetoRepository.findAll();
		return lista;
	}

	public List<ProjetoEntity> findProjetosByStatus(String status) {
		return projetoRepository.findByStatus(status);
	}

	public void validarInformacoesProjeto(ProjetoEntity projeto) {
		validarRisco(projeto.getRisco());
		validarStatus(projeto.getStatus());
	}

	private void validarRisco(String risco) {
		List<String> riscosPermitidos = Arrays.asList("baixo", "médio", "alto");
		if (!riscosPermitidos.contains(risco.toLowerCase())) {
			throw new ResourceBadRequestException("Risco inválido. Os riscos permitidos são: baixo, médio, alto");
		}
	}

	private void validarStatus(String status) {
		List<String> statusPermitidos = Arrays.asList("em análise", "análise realizada", "análise aprovada", "iniciado",
				"planejado", "em andamento", "encerrado", "cancelado");

		if (!statusPermitidos.contains(status.toLowerCase())) {
			throw new ResourceBadRequestException(
					"Status inválido. Os status permitidos são: em análise, análise realizada, análise aprovada, iniciado, planejado, em andamento, encerrado, cancelado");
		}
	}

	public ProjetoEntity saveProjeto(ProjetoEntity projeto) {
		if (projeto.getId() != null) {
			return updateProjeto(projeto, "Permitido apenas atualizar projeto com gerente funcionário");
		}

		validarGerenteFuncionario(projeto.getGerente().getId(), "Permitido apenas inserir funcionário");
		this.validarInformacoesProjeto(projeto);
		return projetoRepository.save(projeto);
	}

	public ProjetoEntity updateProjeto(ProjetoEntity projeto, String mensagem) {
		validarGerenteFuncionario(projeto.getGerente().getId(), mensagem);
		this.validarInformacoesProjeto(projeto);
		return projetoRepository.save(projeto);
	}

	private void validarGerenteFuncionario(Long gerenteId, String mensagem) {
		if (!pessoaService.isFuncionario(gerenteId)) {
			throw new ResourceBadRequestException(mensagem);
		}
	}

	public void excluirProjeto(Long projetoId) {
		ProjetoEntity projeto = projetoRepository.findById(projetoId)
				.orElseThrow(() -> new ResourceBadRequestException("Projeto não encontrado"));

		validarExclusao(projeto.getStatus());

		membropository.deleteByProjetoIdAndPessoaId(projeto.getId());
		projetoRepository.delete(projeto);
	}

	private void validarExclusao(String status) {
		List<String> statusNaoPermitidosExclusao = Arrays.asList("iniciado", "em andamento", "encerrado");

		if (statusNaoPermitidosExclusao.contains(status.toLowerCase())) {
			throw new ResourceBadRequestException("Não é permitido excluir um projeto com status: " + status);
		}
	}

}

