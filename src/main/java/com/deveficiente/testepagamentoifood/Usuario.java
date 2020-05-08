package com.deveficiente.testepagamentoifood;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.util.Assert;

import com.deveficiente.testepagamentoifood.listapagamentos.PossivelRestricaoPagamento;

@Entity
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private @NotBlank String nome;
	@Size(min = 1)
	@ElementCollection
	private Set<TipoPagamento> tiposPagamento = new HashSet<>();

	@Deprecated
	public Usuario() {

	}

	public Usuario(@NotBlank String nome,
			@Size(min = 1) TipoPagamento... possiveisTiposPagamento) {
		this.nome = nome;
		this.tiposPagamento.addAll(Stream.of(possiveisTiposPagamento)
				.collect(Collectors.toSet()));
	}

	public String getNome() {
		return nome;
	}

	public Set<TipoPagamento> pagamentosPossiveisParaRestaurante(
			Restaurante restaurante,
			Collection<PossivelRestricaoPagamento> possiveisRestricoes) {
		return tiposPagamento.stream().filter(restaurante::aceita)

				.filter(tipoPagamento -> {
					return possiveisRestricoes.stream()
							.allMatch(restricao -> restricao.aceita(this,
									tipoPagamento));

				}).collect(Collectors.toSet());
	}

	public boolean podePagarComForma(Restaurante restauranteEscolhido,
			TipoPagamento tipoPagamento,
			List<PossivelRestricaoPagamento> possiveisRestricoes) {
		return pagamentosPossiveisParaRestaurante(restauranteEscolhido,
				possiveisRestricoes).contains(tipoPagamento);
	}
}
