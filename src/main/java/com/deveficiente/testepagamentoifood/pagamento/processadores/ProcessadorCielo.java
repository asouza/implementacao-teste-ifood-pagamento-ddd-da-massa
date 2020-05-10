package com.deveficiente.testepagamentoifood.pagamento.processadores;

import java.util.Optional;

import com.deveficiente.testepagamentoifood.TipoPagamento;
import com.deveficiente.testepagamentoifood.pagamento.AutorizadorDeTransacoes;
import com.deveficiente.testepagamentoifood.pagamento.TentativaPagamento;

public class ProcessadorCielo implements ProcessadorPagamento {

	private AutorizadorDeTransacoes autorizador;

	public ProcessadorCielo(AutorizadorDeTransacoes autorizador) {
		super();
		this.autorizador = autorizador;
	}

	@Override
	public Optional<Pagador> aceita(TentativaPagamento tentativaPagamento) {
		TipoPagamento tipoPagamento = tentativaPagamento.getTipoPagamento();
		if (tipoPagamento.aceitaOnline
				&& !tipoPagamento.equals(TipoPagamento.hipercard)) {
			return Optional.of(new GatewayCielo(autorizador,tentativaPagamento));
		}

		return Optional.empty();
	}

}
