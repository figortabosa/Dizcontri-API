package com.exemple.dizcontre.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.exemple.dizcontre.api.model.Pessoa;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long>{

	@Query(value = "select p from Pessoa p where upper(trim(p.nome)) like %?1%")
	List<Pessoa> pesquisarPorNome(String nome);
}
