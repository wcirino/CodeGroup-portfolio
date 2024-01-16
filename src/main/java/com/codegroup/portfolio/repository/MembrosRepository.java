package com.codegroup.portfolio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.codegroup.portfolio.entity.MembrosEntity;

import jakarta.transaction.Transactional;

public interface MembrosRepository extends JpaRepository<MembrosEntity, Long> {

	@Transactional
	@Modifying
	@Query("DELETE FROM MembrosEntity m WHERE m.idProjeto = :idProjeto")
	void deleteByProjetoIdAndPessoaId(Long idProjeto);

	@Transactional
	@Modifying
	@Query("INSERT INTO MembrosEntity m (m.idProjeto, m.idPessoa) VALUES (:idProjeto, :idPessoa)")
	void insertMembro(Long idProjeto, Long idPessoa);
}
