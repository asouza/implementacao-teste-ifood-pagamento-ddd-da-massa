package com.deveficiente.testepagamentoifood.pagamento;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class TodosProcessadoresPagamento {

	private Set<ProcessadorPagamento> processadores = new HashSet<>();

	@Autowired
	public TodosProcessadoresPagamento(
			Collection<ProcessadorPagamento> processadores) {
		this.processadores.addAll(processadores);

	}

	public Transacao paga(TentativaPagamento tentativaPagamento) {

		TreeSet<Pagador> pagadores = processadores.stream()
				.map(processador -> processador.aceita(tentativaPagamento))
				.filter(Optional :: isPresent)
				.map(Optional :: get)
				.collect(Collectors.toCollection(() -> new TreeSet<>()));

		Assert.isTrue(!pagadores.isEmpty(),
				"Precisa existir pelo menos um objeto do tipo Pagador disponível para realizar o pagamento. #bug");

		return pagadores.iterator().next().paga();
	}

}
