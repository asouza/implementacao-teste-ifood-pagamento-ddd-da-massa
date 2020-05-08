package com.deveficiente.testepagamentoifood.pagamento;

import java.util.Optional;

/*
 * Responsável pelo aceite e geração de um pagador para uma determinada forma de pagamento 
 */
public interface ProcessadorPagamento {

	/**
	 * 
	 * @param tentativaPagamento
	 * @return caso possa processar, retorna a Transacao
	 */
	Optional<Pagador> aceita(TentativaPagamento tentativaPagamento);
	
	
}
