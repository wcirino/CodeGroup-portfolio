package com.codegroup.portfolio.specification;

import org.springframework.data.jpa.domain.Specification;

import com.codegroup.portfolio.entity.ProjetoEntity;

public class ProjetoSpecifications {
	
	public static Specification<ProjetoEntity> nomeLike(String nome) {
	    return (root, query, criteriaBuilder) ->
	        nome != null ? criteriaBuilder.like(root.get("nome"), "%" + nome + "%") : null;
	}

	public static Specification<ProjetoEntity> statusLike(String status) {
	    return (root, query, criteriaBuilder) ->
	        status != null ? criteriaBuilder.like(root.get("status"), "%" + status + "%") : null;
	}

	public static Specification<ProjetoEntity> riscoEqual(String risco) {
	    return (root, query, criteriaBuilder) ->
	        risco != null ? criteriaBuilder.equal(root.get("risco"), risco) : null;
	}

	
}
