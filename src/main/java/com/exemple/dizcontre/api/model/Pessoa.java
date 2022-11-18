package com.exemple.dizcontre.api.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.UniqueElements;
import org.hibernate.validator.constraints.br.CPF;

import com.exemple.dizcontre.api.enuns.SexoEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;


@Entity
@Table(name = "pessoa", uniqueConstraints= {
        @UniqueConstraint(columnNames ={"cpf"})
})        
public class Pessoa implements Serializable{

	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "cod_pessoa")
	private Long cod_pessoa;
	
	
	@Column(name = "nome")
	@NotNull
	private String nome;
	
	
	@Column(name = "cpf", unique = true)
	private String cpf;
	
	
	@Column(name = "data_nascimento")
	private Date data_nascimento;
	
	
	@Column(name = "sexo")
	@Enumerated(EnumType.STRING)
	private SexoEnum sexo;
	
	
	@OneToOne(mappedBy = "pessoa", cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.EAGER )
	private Endereco endereco;
	
	
	@OneToOne(mappedBy = "pessoa", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	private Contato contato;

	public Long getCod_pessoa() {
		return cod_pessoa;
	}

	public void setCod_pessoa(Long cod_pessoa) {
		this.cod_pessoa = cod_pessoa;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Date getData_nascimento() {
		return data_nascimento;
	}

	public void setData_nascimento(Date data_nascimento) {
		this.data_nascimento = data_nascimento;
	}

	public SexoEnum getSexo() {
		return sexo;
	}

	public void setSexo(SexoEnum sexo) {
		this.sexo = sexo;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}


	public Contato getContato() {
		return contato;
	}

	public void setContato(Contato contato) {
		this.contato = contato;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cod_pessoa == null) ? 0 : cod_pessoa.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pessoa other = (Pessoa) obj;
		if (cod_pessoa == null) {
			if (other.cod_pessoa != null)
				return false;
		} else if (!cod_pessoa.equals(other.cod_pessoa))
			return false;
		return true;
	}
	
}
