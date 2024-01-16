package com.codegroup.portfolio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codegroup.portfolio.entity.MembrosEntity;
import com.codegroup.portfolio.service.MembrosService;

@RestController
@RequestMapping("/api-membros")
public class MembrosController {

	@Autowired
	private MembrosService membrosService;

	@PostMapping("/associar-membro-projeto")
	public ResponseEntity<?> associarMembrosAoProjeto(@RequestBody MembrosEntity membrosEntity) {

		membrosService.associarMembrosAoProjeto(membrosEntity);
		return new ResponseEntity<>("Associação realizada com sucesso", HttpStatus.CREATED);
	}
}
