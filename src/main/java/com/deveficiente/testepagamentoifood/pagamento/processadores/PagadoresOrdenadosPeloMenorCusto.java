package com.deveficiente.testepagamentoifood.pagamento.processadores;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.deveficiente.testepagamentoifood.pagamento.TentativaPagamento;

public class PagadoresOrdenadosPeloMenorCusto {

	private Set<ProcessadorPagamento> processadores = new HashSet<>();
	
	@Autowired
	public PagadoresOrdenadosPeloMenorCusto(Set<ProcessadorPagamento> processadores) {
		this.processadores.addAll(processadores);
	}
	
	public TreeSet<Pagador> filtra(TentativaPagamento tentativaPagamento){
		Assert.notNull(tentativaPagamento, "A tentativa de pagamento não pode ser nula");
		
		TreeSet<Pagador> pagadores = processadores.stream()
				.map(processador -> processador.aceita(tentativaPagamento))
				.filter(Optional :: isPresent)
				.map(Optional :: get)
				.collect(Collectors.toCollection(() -> new TreeSet<>()));
		
		Assert.isTrue(!pagadores.isEmpty(),
				"Precisa existir pelo menos um objeto do tipo Pagador disponível para realizar o pagamento. #bug");	
		
		return pagadores;
	}
}
