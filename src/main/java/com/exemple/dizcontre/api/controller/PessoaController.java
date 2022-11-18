package com.exemple.dizcontre.api.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.exemple.dizcontre.api.model.Pessoa;
import com.exemple.dizcontre.api.repository.EnderecoRepository;
import com.exemple.dizcontre.api.repository.PessoaRepository;
import com.exemple.dizcontre.api.service.PessoaService;

@RestController
@RequestMapping("/pessoas")
//@CrossOrigin(origins= {"*"}, maxAge = 4800, allowCredentials = "false"  ) 
public class PessoaController {

	@Autowired
	private PessoaRepository pessoaRepository;

	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private PessoaService pessoaService;

	public List<Pessoa> listar() {
		return pessoaRepository.findAll();
	}

	@PostMapping
	public ResponseEntity<Pessoa> salvar(@Valid @RequestBody Pessoa pessoa) {

	//	try {
		/* Amarra o objeto filho ao objeto pai */
		pessoa.getEndereco().setPessoa(pessoa);

		if (pessoa.getContato() != null) {
			pessoa.getContato().setPessoa(pessoa); /* Amarra o objeto filho ao objeto pai */
		}
		Pessoa pessoaSalva = pessoaRepository.save(pessoa);

		return ResponseEntity.status(HttpStatus.CREATED).body(pessoaSalva);
	//	}catch (Exception e) {
	//		e.getMessage();
	//		e.getStackTrace();
		//}
		//return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@GetMapping
	public ResponseEntity<List<Pessoa>> pessoas() {

		List<Pessoa> list = pessoaRepository.findAll();

		return new ResponseEntity<List<Pessoa>>(list, HttpStatus.OK);
	}
	
	@GetMapping("/{codigo}")
	public ResponseEntity<Pessoa> pesquisaPorCodigo(@PathVariable Long codigo) {
		
		Optional<Pessoa> pessoa = pessoaRepository.findById(codigo);
		return new ResponseEntity<Pessoa>((pessoa.get()),HttpStatus.OK);
	}
	
	
	@GetMapping(value = "pesquisaPorNome")
	public ResponseEntity<List<Pessoa>> pesquisaPorNome(@RequestParam(name = "nome") String nome) {
		
		List<Pessoa> pessoa = pessoaRepository.pesquisarPorNome(nome.trim().toUpperCase());
		return new ResponseEntity<List<Pessoa>>(pessoa,HttpStatus.OK);
}
	
	@PutMapping("/{codigo}")
	public ResponseEntity<Pessoa> atualizar(@PathVariable Long codigo, @RequestBody Pessoa pessoa) {
	
		Pessoa pessoaSalva = pessoaService.atualizar(pessoa, codigo);
		return ResponseEntity.ok(pessoaSalva);
	}
	
	@DeleteMapping("/{codigo}")
	public String excluir(@PathVariable Long codigo) {
		 pessoaRepository.deleteById(codigo);
		 return "Ok";
	}
}
