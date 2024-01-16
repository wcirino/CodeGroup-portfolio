package com.codegroup.portfolio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codegroup.portfolio.entity.PessoaEntity;
import com.codegroup.portfolio.repository.PessoaRepository;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    public boolean isFuncionario(Long pessoaId) {
        PessoaEntity pessoa = pessoaRepository.getIdFuncionario(pessoaId);
        return pessoa != null && pessoa.isFuncionario();
    }
}
