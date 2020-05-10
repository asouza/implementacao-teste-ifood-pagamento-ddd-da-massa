package com.deveficiente.testepagamentoifood.pagamento.processadores;

import java.util.Optional;

import javax.validation.constraints.NotNull;

import com.deveficiente.testepagamentoifood.TipoPagamento;
import com.deveficiente.testepagamentoifood.pagamento.AutorizadorDeTransacoes;
import com.deveficiente.testepagamentoifood.pagamento.TentativaPagamento;

public class ProcessadorRede implements ProcessadorPagamento {

	private AutorizadorDeTransacoes autorizadorDeTransacoes;

	public ProcessadorRede(AutorizadorDeTransacoes autorizadorDeTransacoes) {
		this.autorizadorDeTransacoes = autorizadorDeTransacoes;
	}

	@Override
	public Optional<Pagador> aceita(@NotNull TentativaPagamento tentativaPagamento) {
		TipoPagamento tipoPagamento = tentativaPagamento.getTipoPagamento();
		
		if(tipoPagamento.aceitaOnline && !tipoPagamento.equals(TipoPagamento.elo)) {
			return Optional.of(new GatewayRede(autorizadorDeTransacoes,tentativaPagamento));
		}
		
		return Optional.empty();
	}

}
