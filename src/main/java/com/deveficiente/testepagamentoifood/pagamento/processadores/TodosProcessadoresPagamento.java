package com.deveficiente.testepagamentoifood.pagamento.processadores;

import java.util.TreeSet;
import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deveficiente.testepagamentoifood.pagamento.TentativaPagamento;
import com.deveficiente.testepagamentoifood.pagamento.Transacao;

import feign.FeignException;

@Service
public class TodosProcessadoresPagamento {

	@Autowired
	private PagadoresOrdenadosPeloMenorCusto pagadoresOrdenadosPeloMenorCusto;

	private static final Logger log = LoggerFactory
			.getLogger(TodosProcessadoresPagamento.class);

	public CompletableFuture<Transacao> paga(
			TentativaPagamento tentativaPagamento) {

		TreeSet<Pagador> pagadoresFiltrados = pagadoresOrdenadosPeloMenorCusto
				.filtra(tentativaPagamento);

		for (Pagador pagador : pagadoresFiltrados) {
			try {
				CompletableFuture<Transacao> novaTransacao = pagador.paga();
				log.info(
						"Pagamento realizado com sucesso para {} na tentativa {}",
						pagador, tentativaPagamento);
				return novaTransacao;
				
			} catch (FeignException e) {
				log.error(
						"Problema de rede enquanto tentava pagar com {} a tentiva {} => {}",
						pagador, tentativaPagamento, e);
			} catch (Exception e) {
				log.error("Azedou o mingau enquanto tentava pagar {} => {}",
						tentativaPagamento, e);
			}
		}

		log.error("Não foi possível realizar o pagamento para {}",
				tentativaPagamento);
		return CompletableFuture.completedFuture(new Transacao());
	}

}
