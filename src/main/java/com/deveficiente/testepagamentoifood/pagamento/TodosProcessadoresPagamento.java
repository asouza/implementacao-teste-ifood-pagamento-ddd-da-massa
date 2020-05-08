package com.deveficiente.testepagamentoifood.pagamento;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import feign.FeignException;

@Service
public class TodosProcessadoresPagamento {

	private Set<ProcessadorPagamento> processadores = new HashSet<>();
	
	private static final Logger log = LoggerFactory
			.getLogger(TodosProcessadoresPagamento.class);


	@Autowired
	public TodosProcessadoresPagamento(
			Collection<ProcessadorPagamento> processadores) {
		this.processadores.addAll(processadores);

	}

	public CompletableFuture<Transacao> paga(TentativaPagamento tentativaPagamento) {

		TreeSet<Pagador> pagadores = processadores.stream()
				.map(processador -> processador.aceita(tentativaPagamento))
				.filter(Optional :: isPresent)
				.map(Optional :: get)
				.collect(Collectors.toCollection(() -> new TreeSet<>()));

		Assert.isTrue(!pagadores.isEmpty(),
				"Precisa existir pelo menos um objeto do tipo Pagador disponível para realizar o pagamento. #bug");

		for (Pagador pagador : pagadores) {
			try {
				CompletableFuture<Transacao> novaTransacao = pagador.paga();
				log.info("Pagamento realizado com sucesso para {} na tentativa {}",pagador,tentativaPagamento);
				return novaTransacao;
			} catch (FeignException e) {
				log.error("Problema de rede enquanto tentava pagar com {} a tentiva {} => {}",pagador,tentativaPagamento,e);
			} catch(Exception e) {
				log.error("Azedou o mingau enquanto tentava pagar {} => {}",tentativaPagamento,e);
			}
		}
		
		log.error("Não foi possível realizar o pagamento para {}",tentativaPagamento);
		return CompletableFuture.completedFuture(new Transacao());
	}

}
