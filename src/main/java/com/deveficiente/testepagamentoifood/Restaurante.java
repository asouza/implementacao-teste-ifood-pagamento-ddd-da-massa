package com.deveficiente.testepagamentoifood;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Size;

import org.springframework.util.Assert;

@Entity
public class Restaurante {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	@ManyToMany
	@Size(min = 1)
	private Set<FormaPagamento> formasPagamento = new HashSet<>();
	
	@Deprecated
	public Restaurante() {

	}

	public Restaurante(String nome, FormaPagamento... possiveisFormasPagamento) {
		this.nome = nome;
		this.formasPagamento.addAll(Stream.of(possiveisFormasPagamento).collect(Collectors.toSet()));
	}
	
	public boolean aceita(FormaPagamento formaPagamento) {
		return this.formasPagamento.contains(formaPagamento);
	}

}
