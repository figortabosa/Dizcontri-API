package com.exemple.dizcontre.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exemple.dizcontre.api.model.Pessoa;
import com.exemple.dizcontre.api.model.Usuario;
import com.exemple.dizcontre.api.repository.UsuarioRepository;

import antlr.collections.List;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "http://localhost:4200/")
public class UsuarioController {
	
	@Autowired
	private UsuarioRepository usuarioRepository; 

	@PostMapping
	public ResponseEntity<Usuario> salvar(@RequestBody Usuario usuario) {
		
		String senhaCriptografada = new BCryptPasswordEncoder().encode(usuario.getSenha());
		usuario.setSenha(senhaCriptografada);
		Usuario usuarioSalvo = usuarioRepository.save(usuario);
		
		return ResponseEntity.status(HttpStatus.OK).body(usuarioSalvo);
	}
	
	@GetMapping
	public ResponseEntity<java.util.List<Usuario>> pessoas() {

		java.util.List<Usuario> list = (java.util.List<Usuario>) usuarioRepository.findAll();

		return new ResponseEntity<java.util.List<Usuario>>(list, HttpStatus.OK);
	}
	
	@PutMapping(value = "/", produces = "application/json")
	public ResponseEntity<Usuario> atualizar( @RequestBody Usuario usuario) {
	
		Usuario userTemporario = usuarioRepository.findUserByLong(usuario.getLogin());
		
		if(!userTemporario.getSenha().equals(usuario.getSenha())) {
			String senhaCriptografada = new BCryptPasswordEncoder().encode(usuario.getSenha());
			usuario.setSenha(senhaCriptografada);
		}
		
		Usuario usuarioSalvo = usuarioRepository.save(usuario);
		return ResponseEntity.status(HttpStatus.OK).body(usuarioSalvo);
	}
}
