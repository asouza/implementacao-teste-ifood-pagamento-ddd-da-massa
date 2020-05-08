package com.deveficiente.testepagamentoifood.pagamento;

import java.math.BigDecimal;
import java.util.concurrent.CompletableFuture;

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
