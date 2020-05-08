package com.deveficiente.testepagamentoifood.pagamento;

import java.util.Optional;

import javax.validation.constraints.NotNull;

import com.deveficiente.testepagamentoifood.TipoPagamento;

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
