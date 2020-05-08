package com.deveficiente.testepagamentoifood.pagamento;

import java.math.BigDecimal;

public class GatewayCielo implements Pagador{

	private TentativaPagamento tentativaPagamento;
	private AutorizadorDeTransacoes autorizador;

	public GatewayCielo(AutorizadorDeTransacoes autorizador, TentativaPagamento tentativaPagamento) {
		this.autorizador = autorizador;
		this.tentativaPagamento = tentativaPagamento;
	}

	@Override
	public BigDecimal custo() {
		return BigDecimal.ONE;
	}

	@Override
	public Transacao paga() {
		autorizador.autoriza(110, true);
		System.out.println("[Cielo] "+tentativaPagamento);
		return new Transacao();
	}

}
