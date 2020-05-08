package com.deveficiente.testepagamentoifood.pagamento;

import java.util.Optional;

import javax.validation.constraints.NotNull;

import com.deveficiente.testepagamentoifood.TipoPagamento;

public class ProcessadorRede implements ProcessadorPagamento {

	@Override
	public Optional<Pagador> aceita(@NotNull TentativaPagamento tentativaPagamento) {
		TipoPagamento tipoPagamento = tentativaPagamento.getTipoPagamento();
		
		if(tipoPagamento.aceitaOnline && !tipoPagamento.equals(TipoPagamento.elo)) {
			return Optional.of(new GatewayRede(tentativaPagamento));
		}
		
		return Optional.empty();
	}

}
