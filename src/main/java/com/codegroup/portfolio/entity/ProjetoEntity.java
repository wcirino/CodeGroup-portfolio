package com.codegroup.portfolio.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "projeto")
public class ProjetoEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "data_inicio")
    private String dataInicio;

    @Column(name = "data_previsao_fim")
    private String previsaoTermino;

    @Column(name = "data_fim")
    private String dataRealTermino;

    @Column(name = "descricao", length = 5000)
    private String descricao;

    @Column(name = "status")
    private String status;

    @Column(name = "orcamento")
    private float orcamento;

    @Column(name = "risco")
    private String risco;

    @ManyToOne
    @JoinColumn(name = "idgerente", nullable = false)
    private PessoaEntity gerente;

	public ProjetoEntity(Long id, String nome, String dataInicio, String previsaoTermino, String dataRealTermino,
			String descricao, String status, float orcamento, String risco, PessoaEntity gerente) {
		super();
		this.id = id;
		this.nome = nome;
		this.dataInicio = dataInicio;
		this.previsaoTermino = previsaoTermino;
		this.dataRealTermino = dataRealTermino;
		this.descricao = descricao;
		this.status = status;
		this.orcamento = orcamento;
		this.risco = risco;
		this.gerente = gerente;
	}

	public ProjetoEntity() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(String dataInicio) {
		this.dataInicio = dataInicio;
	}

	public String getPrevisaoTermino() {
		return previsaoTermino;
	}

	public void setPrevisaoTermino(String previsaoTermino) {
		this.previsaoTermino = previsaoTermino;
	}

	public String getDataRealTermino() {
		return dataRealTermino;
	}

	public void setDataRealTermino(String dataRealTermino) {
		this.dataRealTermino = dataRealTermino;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public float getOrcamento() {
		return orcamento;
	}

	public void setOrcamento(float orcamento) {
		this.orcamento = orcamento;
	}

	public String getRisco() {
		return risco;
	}

	public void setRisco(String risco) {
		this.risco = risco;
	}

	public PessoaEntity getGerente() {
		return gerente;
	}

	public void setGerente(PessoaEntity gerente) {
		this.gerente = gerente;
	}
	
	
    
    
}

