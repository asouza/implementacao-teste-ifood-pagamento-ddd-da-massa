package com.deveficiente.testepagamentoifood;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.util.Assert;

@Entity
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private @NotBlank String nome;
	@ManyToMany
	@Size(min = 1)
	private Set<FormaPagamento> formasPagamento = new HashSet<>();

	@Deprecated
	public Usuario() {

	}

	public Usuario(@NotBlank String nome,
			@Size(min = 1) FormaPagamento... possiveisFormasPagamento) {
		this.nome = nome;
		this.formasPagamento.addAll(Stream.of(possiveisFormasPagamento)
				.collect(Collectors.toSet()));
	}

	public Set<FormaPagamento> pagamentosPossiveisParaRestaurante(
			Restaurante restaurante) {
		return formasPagamento.stream().filter(restaurante::aceita)
				.collect(Collectors.toSet());
	}
}
