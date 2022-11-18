package com.exemple.dizcontre.api.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exemple.dizcontre.api.model.Pessoa;
import com.exemple.dizcontre.api.repository.PessoaRepository;


@Service
public class PessoaService {

	@Autowired
	private PessoaRepository pessoaRepository;
	
	public Pessoa atualizar(Pessoa pessoa, Long codigo) {
		
		Optional<Pessoa> pessoaSalva = pessoaRepository.findById(codigo);
		BeanUtils.copyProperties(pessoa, pessoaSalva.get(), "cod_pessoa");
		if(pessoaSalva.get().getContato() != null) {
			pessoaSalva.get().getContato().setPessoa(pessoaSalva.get());
		}
		if(pessoaSalva.get().getEndereco() != null) {
			pessoaSalva.get().getEndereco().setPessoa(pessoaSalva.get());
		}
		return pessoaRepository.save(pessoaSalva.get());
	}
}
