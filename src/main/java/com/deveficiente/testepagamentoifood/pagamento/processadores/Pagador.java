package com.deveficiente.testepagamentoifood.pagamento.processadores;

import java.math.BigDecimal;
import java.util.concurrent.CompletableFuture;

import com.deveficiente.testepagamentoifood.pagamento.TentativaPagamento;
import com.deveficiente.testepagamentoifood.pagamento.Transacao;

/**
 * Representa um transacionador(essa palavra existe?) em si. Acquiers, subacquirer, paga com maquina etc.
 * As implementações dessa interface precisam de uma {@link TentativaPagamento} com variável de instância
 * @author albertoluizsouza
 *
 */
public interface Pagador extends Comparable<Pagador>{

	BigDecimal custo();
	
	CompletableFuture<Transacao> paga();
	
	@Override
		default int compareTo(Pagador outro) {
			return this.custo().compareTo(outro.custo());
		}
}
