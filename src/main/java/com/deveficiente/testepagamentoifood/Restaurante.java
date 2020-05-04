package com.deveficiente.testepagamentoifood;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.springframework.util.Assert;

@Entity
public class Restaurante {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	@ManyToMany
	private Set<FormaPagamento> formasPagamento = new HashSet<>();

	public Restaurante(String nome) {
		this.nome = nome;
	}

	public void adicionaPagamento(FormaPagamento formaPagamento) {
		Assert.isTrue(!formasPagamento.contains(formaPagamento),"Já adicionou essa");
		this.formasPagamento.add(formaPagamento);
	}

}
