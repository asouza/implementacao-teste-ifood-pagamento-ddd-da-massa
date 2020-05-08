package com.deveficiente.testepagamentoifood.pagamento;

import java.math.BigDecimal;

public class GatewayCielo implements Pagador{

	private TentativaPagamento tentativaPagamento;

	public GatewayCielo(TentativaPagamento tentativaPagamento) {
		this.tentativaPagamento = tentativaPagamento;
	}

	@Override
	public BigDecimal custo() {
		return BigDecimal.ONE;
	}

	@Override
	public Transacao paga() {
		System.out.println("[Cielo] "+tentativaPagamento);
		return new Transacao();
	}

}
