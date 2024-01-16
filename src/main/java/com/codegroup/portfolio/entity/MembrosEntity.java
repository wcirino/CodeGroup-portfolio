package com.codegroup.portfolio.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "membros")
public class MembrosEntity implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "idprojeto")
    private Long idProjeto;
	
    @Column(name = "idpessoa ")
    private Long idPessoa;

	public MembrosEntity() {
		super();
	}
	
	public MembrosEntity(Long idProjeto, Long idPessoa) {
		super();
		this.idProjeto = idProjeto;
		this.idPessoa = idPessoa;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Long getIdProjeto() {
		return idProjeto;
	}

	public void setIdProjeto(Long idProjeto) {
		this.idProjeto = idProjeto;
	}

	public Long getIdPessoa() {
		return idPessoa;
	}

	public void setIdPessoa(Long idPessoa) {
		this.idPessoa = idPessoa;
	}
    
	
    
}
